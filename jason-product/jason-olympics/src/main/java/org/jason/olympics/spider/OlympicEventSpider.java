package org.jason.olympics.spider;

import org.jason.olympics.model.OlympicEvent;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OlympicEventSpider {
    private static Logger LOGGER = LoggerFactory.getLogger(OlympicEventSpider.class);
    private String olympicCode;
    private String baseURL = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/medal-standings.htm";
    private OlympicEventSpider(String olympicCode){
        this.olympicCode = olympicCode;
    }

    public static OlympicEventSpider build(String olympicCode){
        return new OlympicEventSpider(olympicCode);
    }

    public List<OlympicEvent> getOlympicEvent() {
        List<String> goldMedalByCountry = resolveGoldMedalByCountry();

        List<OlympicEvent> events = resolveEvents(goldMedalByCountry);

        return events;
    }

    private List<OlympicEvent> resolveEvents(List<String> goldMedalByCountry) {
        List<OlympicEvent> events = new ArrayList<>();
        for (String currentCountryByMedal : goldMedalByCountry) {
            Document eventDoc = null;
            try {
                eventDoc = SpiderUtil.crawlPage(currentCountryByMedal);
            } catch (IOException e) {
                LOGGER.error("Error when crawling page:  " + baseURL, e);

                return null;
            }

            Elements rowElements = eventDoc.body().select("#mainContainer #Medallist_by_sport_null tbody tr");

            for (Element currentRow : rowElements) {
                String sportCode = currentRow.select(":nth-child(2) a").last().text();
                String event = currentRow.select(".StyleCenter").first().text();
                OlympicEvent runningEvent =  OlympicEvent.build(sportCode, event);
                events.add(runningEvent);
            }

        }

        return events;
    }


    private List<String> resolveGoldMedalByCountry(){
        Document medalDocument = null;
        try {
            medalDocument = SpiderUtil.crawlPage(baseURL);
        } catch (IOException e) {
            LOGGER.error("Error when crawling page:  " + baseURL, e);

            return null;
        }

        List<String> medalByCountry = new ArrayList<>();
        Elements allRows = medalDocument.body().select("div table tbody tr :nth-child(3) :nth-child(1)");
        for(Element currentRow : allRows){
            String goldMedalByCountry = currentRow.attr("abs:href");
            if (goldMedalByCountry.endsWith("gold-medal.htm")){
                medalByCountry.add(goldMedalByCountry);
            }
        }
        return medalByCountry;
    }
}
