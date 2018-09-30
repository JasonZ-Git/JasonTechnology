package org.jason.birthday;

import java.io.IOException;
import java.time.LocalDate;
import org.jason.util.JasonGoogleCalendarUtil;
import com.google.api.client.util.DateTime;

public class CreateBirthdayCalendarEventApp {

  public static void createCurrentYearBirthdayEvent(Relative relative) throws IOException {
    LocalDate currentYearBirthday = relative.getBirthday().getBirthdayOfYear(LocalDate.now().getYear());
    String startTime = currentYearBirthday.atTime(15, 0, 0).toString() + ":00+08:00";
    String endTime = currentYearBirthday.atTime(18, 0, 0).toString() + ":00+08:00";
    DateTime gStartTime = new DateTime(startTime);
    DateTime gEndTime = new DateTime(endTime);
    JasonGoogleCalendarUtil.createEvent(gStartTime, gEndTime, relative.getName() + "生日");
  }

}
