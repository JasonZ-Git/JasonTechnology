package org.jason.test;

import java.io.IOException;
import java.util.List;

import org.jason.util.JasonGoogleCalendarUtil;
import org.junit.Test;
import org.springframework.util.Assert;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

public class CalendarTest {

  @Test
  public void testGetEvent() throws IOException {
    DateTime start = new DateTime("2016-10-18T09:00:00+13:00");
    DateTime end = new DateTime("2016-11-18T10:00:00+13:00");
    // createEvents(startDateTime, endDateTime, "Test");
    List<Event> events = JasonGoogleCalendarUtil.getEvents(start, end);
    
    Assert.isTrue(events.size() >1);
  }

  @Test
  public void testCreateEvent() throws IOException {
    DateTime start = new DateTime("2016-11-18T09:00:00+13:00");
    DateTime end = new DateTime("2016-11-18T10:00:00+13:00");
    List<Event> original = JasonGoogleCalendarUtil.getEvents(start, end);
    
    Event createdEvent = JasonGoogleCalendarUtil.createEvent(start, end, "Test Calendar");
    
    List<Event> newEvent = JasonGoogleCalendarUtil.getEvents(start, end);
    
    Assert.isTrue(newEvent.size() - original.size() == 1);
    
    JasonGoogleCalendarUtil.deleteEvent(createdEvent.getId());
  }
}
