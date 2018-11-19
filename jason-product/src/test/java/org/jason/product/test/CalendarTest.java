package org.jason.product.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import org.jason.util.calendar.JasonCalendarUtil;
import org.junit.Test;
import org.springframework.util.Assert;
import com.google.api.services.calendar.model.Event;

public class CalendarTest {

  @Test
  public void testGetEvent() throws IOException, GeneralSecurityException {
    List<Event> events = JasonCalendarUtil.getEvents(10);
    
    Assert.isTrue(events.size() >1);
  }
}
