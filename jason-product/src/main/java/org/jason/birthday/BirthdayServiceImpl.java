package org.jason.birthday;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jason.util.JasonCalendarUtil;

import com.google.api.client.util.DateTime;

public class BirthdayServiceImpl implements BirthdayService {

  private static final Relative MOTHER = new Relative("妈妈", 1957, 8, 5, true);
  private static final Relative GRANDMA = new Relative("奶奶", 1929, 10, 12, true);
  private static final Relative FATHER = new Relative("爸爸", 1959, 12, 9, true);

  private static List<Relative> ALL_RELATIVES = Arrays.asList(MOTHER, GRANDMA, FATHER);

  @Override
  public void createCurrentYearCalendar() throws IOException {
    for (int i = 0; i < 30; ++i) {
      LocalDate birthday = FATHER.getBirthday().getBirthdayOfYear(LocalDate.now().getYear() + i);
      String startTime = birthday.atTime(15, 0, 0).toString() + ":00+08:00";
      String endTime = birthday.atTime(18, 0, 0).toString() + ":00+08:00";
      DateTime gStartTime = new DateTime(startTime);
      DateTime gEndTime = new DateTime(endTime);
      JasonCalendarUtil.createEvent(gStartTime, gEndTime, FATHER.getName() + "生日");
    }
  }

}
