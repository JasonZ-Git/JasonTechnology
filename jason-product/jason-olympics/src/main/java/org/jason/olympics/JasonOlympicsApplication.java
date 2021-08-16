package org.jason.olympics;

import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.olympics.spider.OlympicEventSpider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JasonOlympicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasonOlympicsApplication.class, args);

		getEventResult();
	}

	private static void getEventResult(){
		List<OlympicGameResult> gameResult = OlympicEventSpider.build("tokyo-2020").getOlympicResult();

		String getEventId = "select id from olympic_event where name = \"%s\" and sport_code = \"%s\",";
		String sqlFormat = "insert into olympic_event_result(event_id, country_code, medal_type) " + getEventId + "\"%s\", \"%s\"";
		gameResult.stream().map(item -> String.format(sqlFormat, item.getEventName(),item.getSportCode(),item.getCountry(), item.getMedal())).forEach(System.out::println);
	}

	private static void getEvents(){
		List<OlympicEvent> allEvents = OlympicEventSpider.build("tokyo-2020").getOlympicEvents();

		allEvents.stream().map(item -> String.format("insert into olympic_event(sport_code, name) values(\"%s\", \"%s\");",item.getSportCode(), item.getEventName())).forEach(System.out::println);
	}
}
