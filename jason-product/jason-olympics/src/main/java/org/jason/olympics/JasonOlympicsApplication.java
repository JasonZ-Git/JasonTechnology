package org.jason.olympics;

import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.olympics.spider.OlympicEventSpider;
import org.jason.util.SpiderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JasonOlympicsApplication {

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
					.map(item -> item.stripLeading())
					.collect(Collectors.toList());

			for(String currentAthletesURL : athletesURL) {
				String absoluteAthletePage = getAbsoluteURL(olympicAthleteUrl, currentAthletesURL);
				// Document athletePage = SpiderUtil.crawlPage(currentAthletesURL);

				System.out.println(absoluteAthletePage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getAbsoluteURL(String baseURL, String relativeURL) {
		Path basePath = Paths.get(baseURL).getParent();
		Path athleteURL = basePath.resolve(relativeURL);

		return athleteURL.toAbsolutePath().toString();
	}
}
