package org.jason.util.calendar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JasonDateConverter {

    public static final Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static final LocalDate toLocalDate(final Date date) {

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
