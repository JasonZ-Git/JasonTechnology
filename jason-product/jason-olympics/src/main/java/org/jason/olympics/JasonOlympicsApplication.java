package org.jason.olympics;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.annotation.ToRefactor;
import org.jason.olympics.model.Athlete;
import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.olympics.spider.AtheleteSpider;
import org.jason.olympics.spider.EventSpider;
import org.jason.util.JasonFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ToRefactor("Currently, I am focused on fetching the data, and the logic needs to move out and cleaned as a proper project")
public class JasonOlympicsApplication {

  private static Logger logger = LoggerFactory.getLogger(JasonOlympicsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(JasonOlympicsApplication.class, args);

    getAthletes();
  }

  private static void getEventResult() {
    List<OlympicGameResult> gameResult = new EventSpider("tokyo-2020").getOlympicResult();

    String getEventIdSQL = "(select id from olympic_event where name = \"%s\" and sport_code = \"%s\"),";
    String sqlFormat = "insert into olympic_event_result(event_id, country_code, medal_type) select " + getEventIdSQL + "\"%s\", \"%s\"";
    gameResult.stream().map(item -> String.format(sqlFormat, item.getEventName(), item.getSportCode(), item.getCountry(), item.getMedal())).forEach(System.out::println);
  }

  private static void getEvents() {
    List<OlympicEvent> allEvents = new EventSpider("tokyo-2020").getOlympicEvents();

    allEvents.stream().map(item -> String.format("insert into olympic_event(sport_code, name) values(\"%s\", \"%s\");", item.getSportCode(), item.getEventName())).forEach(System.out::println);
  }

  private static void getAthletes() {
    AtheleteSpider athleteSpider = new AtheleteSpider("tokyo-2020");
    List<Athlete> atheletes = athleteSpider.getAtheletes();
    
    logger.info("Task finished, {} athlete proceed ", atheletes.size());
  }

}
