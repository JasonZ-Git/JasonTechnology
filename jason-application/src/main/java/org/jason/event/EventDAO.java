package org.jason.event;

import java.time.Period;

public interface EventDAO {
  void getEvents(Period period);
}
