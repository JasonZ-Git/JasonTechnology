package org.jason.event;

import java.time.Period;
import java.util.List;

import org.jason.util.JasonGoogleCalendarUtil;

import com.google.api.services.calendar.model.Events;

public class EventServiceImpl implements EventService {

  @Override
  public void createEvent(Event event) {
    // TODO Auto-generated method stub
  }

  @Override
  public void editEvent(Event event) {
    // TODO Auto-generated method stub
  }

  @Override
  public void deleteEvent(String eventId) {
    // TODO Auto-generated method stub
  }
  
  @Override
  public Events getEvents(Period period) {
    return null;
  }

}
