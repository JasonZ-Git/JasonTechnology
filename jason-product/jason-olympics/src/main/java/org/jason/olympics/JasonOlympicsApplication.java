package org.jason.olympics;

import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.spider.OlympicEventSpider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JasonOlympicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasonOlympicsApplication.class, args);

		getEvents();

	}

	private static void getEvents(){
		List<OlympicEvent> allEvents = OlympicEventSpider.build("tokyo-2020").getOlympicEvent();

		allEvents.stream().map(item -> String.format("insert into olympic_event(sport_code, name) values(\"%s\", \"%s\");",item.getSportCode(), item.getEventName())).forEach(System.out::println);
	}
}
