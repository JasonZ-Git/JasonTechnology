package org.jason.code_practice;

import java.util.Calendar;
import java.util.Date;


public class Q3_5_Sort_Stack {
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    Date date = new Date();
    cal.setTime(date);

    cal.set(Calendar.DAY_OF_MONTH, 33);

    System.out.println(cal.get(Calendar.DAY_OF_MONTH));
    System.out.println(cal.get(Calendar.DAY_OF_YEAR));
  }
}
