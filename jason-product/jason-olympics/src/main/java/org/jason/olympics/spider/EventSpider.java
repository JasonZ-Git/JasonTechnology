package org.jason.olympics.spider;

import org.jason.olympics.model.Medal;
import org.jason.olympics.model.OlympicEvent;
import org.jason.olympics.model.OlympicGameResult;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventSpider {
    private static Logger LOGGER = LoggerFactory.getLogger(EventSpider.class);
    private String olympicCode;
    private String baseURL = "https://olympics.com/tokyo-2020/olympic-games/en/results/all-sports/medal-standings.htm";

    public EventSpider(String olympicCode){
        this.olympicCode = olympicCode;
    }

    public List<OlympicEvent> getOlympicEvents() {
        List<String> goldMedalByCountry = resolveMedalByCountry(Medal.GOLD);

        List<OlympicEvent> events = resolveEvents(goldMedalByCountry);

        return events;
    }

    public List<OlympicGameResult> getOlympicResult() {
        List<String> goldMedalByCountry = resolveMedalByCountry(Medal.GOLD);
        List<OlympicGameResult> goldEvents = resolveEventOfCountryEvents(goldMedalByCountry, Medal.GOLD);

        List<String> silverMedalByCountry = resolveMedalByCountry(Medal.SILVER);
        List<OlympicGameResult> silverEvents = resolveEventOfCountryEvents(silverMedalByCountry, Medal.SILVER);

        List<String> bronzeMedalByCountry = resolveMedalByCountry(Medal.BRONZE);
        List<OlympicGameResult> bronzeEvents = resolveEventOfCountryEvents(bronzeMedalByCountry, Medal.BRONZE);

        List<OlympicGameResult> allEvents = new ArrayList<>();
        allEvents.addAll(goldEvents);
        allEvents.addAll(silverEvents);
        allEvents.addAll(bronzeEvents);

        return allEvents;
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

    private List<OlympicGameResult> resolveEventOfCountryEvents(List<String> goldMedalByCountry, Medal medal) {
        List<OlympicGameResult> events = new ArrayList<>();
        for (String currentCountryByMedal : goldMedalByCountry) {
            Document eventDoc = null;
            try {
                eventDoc = SpiderUtil.crawlPage(currentCountryByMedal);
            } catch (IOException e) {
                LOGGER.error("Error when crawling page:  " + baseURL, e);

                return null;
            }

            Elements rowElements = eventDoc.body().select("#mainContainer #Medallist_by_sport_null tbody tr");
            String countryURL =  eventDoc.body().select("#mainContainer .container .NoSport .flagStyleBig").first().attr("src");
            String countryCode = countryURL.substring(countryURL.lastIndexOf("/") + 1).replace(".png","");

            // ROC is RUS
            countryCode = countryCode.equals("ROC")? "RUS" : countryCode;

            for (Element currentRow : rowElements) {
                String sportCode = currentRow.select(":nth-child(2) a").last().text();
                String event = currentRow.select(".StyleCenter").first().text();

                OlympicGameResult runningEvent =  OlympicGameResult.build(sportCode, event, countryCode,  medal);
                events.add(runningEvent);
            }
        }

        return events;
    }

    private List<String> resolveMedalByCountry(Medal medal){
        Document medalDocument = null;
        try {
            medalDocument = SpiderUtil.crawlPage(baseURL);
        } catch (IOException e) {
            LOGGER.error("Error when crawling page:  " + baseURL, e);

            return null;
        }

        List<String> medalByCountry = new ArrayList<>();

        Elements allRows = null;
        switch(medal){
            case GOLD:
                allRows = medalDocument.body().select("div table tbody tr :nth-child(3) :nth-child(1)");
                break;
            case SILVER:
                allRows = medalDocument.body().select("div table tbody tr :nth-child(4) :nth-child(1)");
                break;
            case BRONZE:
                allRows = medalDocument.body().select("div table tbody tr :nth-child(5) :nth-child(1)");
                break;
        }

        for(Element currentRow : allRows){
            String goldMedalByCountry = currentRow.attr("abs:href");
            if (goldMedalByCountry.endsWith(String.format("%s-medal.htm", medal.toString().toLowerCase()))){
                medalByCountry.add(goldMedalByCountry);
            }
        }
        return medalByCountry;
    }
}
