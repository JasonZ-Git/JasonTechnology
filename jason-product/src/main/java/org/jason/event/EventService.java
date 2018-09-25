package org.jason.event;

import java.time.Period;

import com.google.api.services.calendar.model.Events;

public interface EventService {
  void createEvent(Event event);
  void editEvent(Event event);
  void deleteEvent(String eventId);
  
  Events getEvents(Period period);
}
