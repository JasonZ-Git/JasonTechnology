package org.jason.olympics.spider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonObject;
import org.jason.olympics.model.Athlete;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OlympicAtheleteSpider {
  private static final Logger logger = LoggerFactory.getLogger(OlympicAtheleteSpider.class);
  private static final String TOYKO_ATHELETES = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/zzje001a.json";
  
  public static List<Athlete> getAtheletes(){

    List<Athlete> athletes = new ArrayList<>();

    try {
        JsonObject athleteString = SpiderUtil.readJSON(TOYKO_ATHELETES);

        JsonArray bodyData = athleteString.get("data").asJsonArray();

        List<String> athletesURL = bodyData.stream()
                .filter(item -> item.asJsonObject().get("lnk") != null)
                .map(item -> item.asJsonObject().get("lnk").toString())
                .map(item -> item.substring(1, item.length() -1))
                .collect(Collectors.toList());


        for(String currentAthletesURL : athletesURL) {
            String absoluteAthletePage = getAbsoluteURL(TOYKO_ATHELETES, currentAthletesURL);

            Document athletePage =  SpiderUtil.crawlPage(absoluteAthletePage);

            try {
                Athlete athlete = getAthlete(athletePage);
                athletes.add(athlete);
            } catch(Exception e){
                logger.error("Error parsing Page " + absoluteAthletePage, e);
                continue;
            }
        }

    } catch (IOException e) {
        logger.error(e.getMessage());
    }
    
    return athletes;
  }
  
  private static String getAbsoluteURL(String baseURL, String relativeURL) {
    URL result =  null;
    try {
        result = new URL(new URL(baseURL), relativeURL);
    } catch (MalformedURLException e) {

    }

    return result.toString();
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
                Element parent = current.parent();
                String placeOfResidence = null;
                if (parent != null && parent.childNodeSize() == 3) {
                  Node residentNode = parent.childNode(2);
                  if(residentNode instanceof TextNode) {
                    placeOfResidence = ((TextNode)residentNode).text().trim();
                  }
                }

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
}
