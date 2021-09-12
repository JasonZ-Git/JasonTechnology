package org.jason.olympics;

import java.util.List;
import org.jason.annotation.ToRefactor;
import org.jason.olympics.model.Athlete;
import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.olympics.spider.AthleteSpider;
import org.jason.olympics.spider.EventSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ToRefactor("First Refactor is finished, second refactor will make each task controlled from a web page")
public class JasonOlympicsApplication {

  private static Logger logger = LoggerFactory.getLogger(JasonOlympicsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(JasonOlympicsApplication.class, args);

    getEventResult();
    getEvents();
    getAthletes();
  }

  private static void getEventResult() {
    List<OlympicGameResult> gameResult = new EventSpider("tokyo-2020").getEventResults();

    logger.info("Event Resul Task Done, {} records fetched", gameResult.size());
  }

  private static void getEvents() {
    List<OlympicEvent> allEvents = new EventSpider("tokyo-2020").getEvents();

    logger.info("Event Task Done, {} records fetched", allEvents.size());
  }

  private static void getAthletes() {
    List<Athlete> atheletes = new AthleteSpider("tokyo-2020").getAthletes();

    logger.info("Athlete Task Done, {} athlete proceed ", atheletes.size());
  }

}
