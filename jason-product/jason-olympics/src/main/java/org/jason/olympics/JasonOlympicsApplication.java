package org.jason.olympics;

import org.jason.annotation.ToRefactor;
import org.jason.olympics.model.Athlete;
import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.olympics.spider.OlympicEventSpider;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@ToRefactor("Currently, I am focused on fetching the data, and the logic needs to move out and cleaned as a proper project")
public class JasonOlympicsApplication {

	private static Logger logger = LoggerFactory.getLogger(JasonOlympicsApplication.class);

	public static void main(String[] args){
		SpringApplication.run(JasonOlympicsApplication.class, args);

		getAthletes();
	}

	private static void getEventResult(){
		List<OlympicGameResult> gameResult = OlympicEventSpider.build("tokyo-2020").getOlympicResult();

		String getEventIdSQL = "(select id from olympic_event where name = \"%s\" and sport_code = \"%s\"),";
		String sqlFormat = "insert into olympic_event_result(event_id, country_code, medal_type) select " + getEventIdSQL + "\"%s\", \"%s\"";
		gameResult.stream().map(item -> String.format(sqlFormat, item.getEventName(),item.getSportCode(),item.getCountry(), item.getMedal())).forEach(System.out::println);
	}

	private static void getEvents(){
		List<OlympicEvent> allEvents = OlympicEventSpider.build("tokyo-2020").getOlympicEvents();

		allEvents.stream().map(item -> String.format("insert into olympic_event(sport_code, name) values(\"%s\", \"%s\");",item.getSportCode(), item.getEventName())).forEach(System.out::println);
	}

	private static void getAthletes(){
		Jsonb jsonb = JsonbBuilder.create();
		// List<Athlete> athletes = jsonb.fromJson("", new ArrayList<Athlete>(){}.getClass().getGenericSuperclass());

		String olympicAthleteUrl = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/zzje001a.json";

		try {
			JsonObject athleteString = SpiderUtil.readJSON(olympicAthleteUrl);

			JsonArray bodyData = athleteString.get("data").asJsonArray();

			List<String> athletesURL = bodyData.stream()
					.filter(item -> item.asJsonObject().get("lnk") != null)
					.map(item -> item.asJsonObject().get("lnk").toString())
					.map(item -> item.substring(1, item.length() -1))
					.collect(Collectors.toList());

			List<Athlete> athletes = new ArrayList<>();

			for(String currentAthletesURL : athletesURL) {
				String absoluteAthletePage = getAbsoluteURL(olympicAthleteUrl, currentAthletesURL);

				Document athletePage =  SpiderUtil.crawlPage(absoluteAthletePage);

				Athlete athlete = getAthlete(athletePage);

				athletes.add(athlete);

				System.out.println(athlete.toInsertSQLString());
			}

			System.out.printf("There are %d athletes in all ", athletes.size());



		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private static Athlete getAthlete(Document athletePage) {

		Athlete athleteResult = new Athlete();

		String fullName = athletePage.body().select("#mainContainer .container .row h1").text().trim();
		athleteResult.setFullName(fullName);

		String countryCode = athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid .playerTag").first().attr("country");
		athleteResult.setPassportCountry(countryCode);

		String sportName = athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid .my-2 a").first().attr("title");
		athleteResult.setAttendedSport(sportName.split("-")[1].trim());

		Element athleteParentNode =  athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid").first();

		Elements elementsAll = athleteParentNode.children().last().select("label");

		for(Element current : elementsAll) {
			String textLabel = current.text().trim();
			switch(textLabel){
				case "Date of Birth:":
					String dateOfBirth = current.parent().text();
					athleteResult.setDateOfBirth(dateOfBirth.split(":")[1].trim());
					break;
				case "Gender:":
					String gender = current.parent().textNodes().get(1).text().trim();
					athleteResult.setGender(gender);
					break;
				case "Place of birth:":
					String placeOfBirth = current.parent().textNodes().get(1).text().trim();
					athleteResult.setBirthPlace(placeOfBirth);
					break;
				case "Birth Country:":
					String countryOfBirth = current.parent().select(".playerTag").first().attr("country").trim();
					athleteResult.setBirthCountry(countryOfBirth);
					break;
				case "Height (m/ft):":
					List<TextNode> allNodes = current.parent().textNodes();
					String height = allNodes.get(allNodes.size() -1).getWholeText().split("/")[0].trim();
					athleteResult.setHeight(Float.parseFloat(height));
					break;
				case "Place of residence :":
					String placeOfResidence = current.parent().text().trim();
					athleteResult.setResidencePlace(placeOfResidence);
					break;
				case "Residence Country:":
					String residenceCountry = current.parent().select(".playerTag").first().attr("country").trim();
					athleteResult.setResidenceCountry(residenceCountry);
					break;
				case "Age:":
					break;
				default:
					System.out.println("Not recognised, shame: " + textLabel);
					break;
			}
		}

		return athleteResult;
	}

	private static String getAbsoluteURL(String baseURL, String relativeURL) {
		URL result =  null;
		try {
			result = new URL(new URL(baseURL), relativeURL);
		} catch (MalformedURLException e) {

		}

		return result.toString();
	}


}
