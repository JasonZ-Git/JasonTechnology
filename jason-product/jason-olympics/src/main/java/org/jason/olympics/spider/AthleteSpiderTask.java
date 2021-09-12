package org.jason.olympics.spider;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import org.jason.olympics.model.Athlete;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fetch Athlete Data from Olympic athlete page, it depends on the page structure of athlete page
 * 
 * @author Jason Zhang
 */

public class AthleteSpiderTask implements Callable<Athlete> {

  private static final Logger logger = LoggerFactory.getLogger(AthleteSpiderTask.class);
  private static int counter = 0;
  
  private String atheleteURL;

  private AthleteSpiderTask(@Nonnull String atheleteURL) {
    Objects.requireNonNull(atheleteURL);
    
    this.atheleteURL = atheleteURL;}
  
  public static AthleteSpiderTask build(@Nonnull String atheleteURL) {
    Objects.requireNonNull(atheleteURL);

    return new AthleteSpiderTask(atheleteURL);
  }

  @Override
  public Athlete call() {

    Athlete athlete = null;
    try {
      Document atheletePage = SpiderUtil.crawlPage(atheleteURL);
      athlete = parseAthlete(atheletePage);
    } catch (IOException e) {
      logger.error("Error when parsing url, will continue: {}", atheleteURL, e);
    }

    return athlete;
  }

  private Athlete parseAthlete(Document athletePage) {

    Athlete athlete = new Athlete();

    String fullName = athletePage.body().select("#mainContainer .container .row h1").text().trim();
    athlete.setFullName(fullName);

    String countryCode = athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid .playerTag").first().attr("country");
    athlete.setPassportCountry(countryCode);

    String sportName = athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid .my-2 a").first().attr("title");
    athlete.setAttendedSport(sportName.split("-")[1].trim());

    Element athleteParentNode = athletePage.body().select("#mainContainer .container .row .panel-bio .container-fluid").first();

    Elements elementsAll = athleteParentNode.children().last().select("label");

    for (Element current : elementsAll) {
      String label = current.text().trim();
      setAthelete(athlete, label, current);
    }
    
    logger.info("Finished {}", ++counter);

    return athlete;
  }
  
  private void setAthelete(Athlete athlete, String label, Element current) {
    switch (label) {
      case "Date of Birth:":
        String dateOfBirth = current.parent().text();
        athlete.setDateOfBirth(dateOfBirth.split(":")[1].trim());
        break;
      case "Gender:":
        String gender = current.parent().textNodes().get(1).text().trim();
        athlete.setGender(gender);
        break;
      case "Place of birth:":
        String placeOfBirth = current.parent().textNodes().get(1).text().trim();
        athlete.setBirthPlace(placeOfBirth);
        break;
      case "Birth Country:":
        String countryOfBirth = current.parent().select(".playerTag").first().attr("country").trim();
        athlete.setBirthCountry(countryOfBirth);
        break;
      case "Height (m/ft):":
        List<TextNode> allNodes = current.parent().textNodes();
        String height = allNodes.get(allNodes.size() - 1).getWholeText().split("/")[0].trim();
        athlete.setHeight(Float.parseFloat(height));
        break;
      case "Place of residence :":
        Element parent = current.parent();
        String placeOfResidence = null;
        if (parent != null && parent.childNodeSize() == 3) {
          Node residentNode = parent.childNode(2);
          if (residentNode instanceof TextNode) {
            placeOfResidence = ((TextNode) residentNode).text().trim();
          }
        }
        athlete.setResidencePlace(placeOfResidence);
        break;
      case "Residence Country:":
        String residenceCountry = current.parent().select(".playerTag").first().attr("country").trim();
        athlete.setResidenceCountry(residenceCountry);
        break;
      case "Age:":
        break;
      default:
        logger.warn("Not recognised, shame: " + label);
        break;
    }
  }
}
