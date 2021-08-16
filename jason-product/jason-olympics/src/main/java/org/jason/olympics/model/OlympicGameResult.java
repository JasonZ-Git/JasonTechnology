package org.jason.olympics.model;

public class OlympicGameResult {
    private String sportCode;
    private String eventName;
    private String countryCode;
    private Medal medal;

    private OlympicGameResult(String sportCode, String eventName, String countryCode, Medal medal){
        this.sportCode = sportCode;
        this.eventName = eventName;
        this.countryCode = countryCode;
        this.medal = medal;
    }

    public static OlympicGameResult build(String sportCode, String eventName, String countryCode, Medal medal){
        return new OlympicGameResult(sportCode, eventName, countryCode, medal);
    }

    public String getSportCode() {
        return sportCode;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCountry() {
        return countryCode;
    }

    public Medal getMedal() {
        return medal;
    }

    @Override
    public String toString() {
        return String.format("sportCode is: %s, eventName is: %s", sportCode, eventName);
    }
}
