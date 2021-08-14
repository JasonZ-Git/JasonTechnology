package org.jason.olympics.model;

public class OlympicEvent {
    private String sportCode;
    private String eventName;
    private String olympicCode;

    private OlympicEvent(String sportCode, String eventName){
        this.sportCode = sportCode;
        this.eventName = eventName;
    }

    public static OlympicEvent build(String sportCode, String eventName){
        return new OlympicEvent(sportCode, eventName);
    }

    public String getSportCode() {
        return sportCode;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return String.format("sportCode is: %s, eventName is: %s", sportCode, eventName);
    }
}
