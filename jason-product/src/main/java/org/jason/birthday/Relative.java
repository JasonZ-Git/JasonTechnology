package org.jason.birthday;

import java.time.LocalDate;

import org.jason.finalclass.ChineseCalendar;

public class Relative {
  private String name;
  private Birthday birthday;
  private int birthYear;

  private Relative(String name, int birthYear, Birthday birthday) {
    this.name = name;
    this.birthYear = birthYear;
    this.birthday = birthday;
  }

  public Relative(String name, int birthYear, int month, int date, boolean isChineseCalendar) {
    this(name, birthYear, Birthday.from(month, date, isChineseCalendar));
  }

  public Birthday getBirthday() {
    return this.birthday;
  }

  public int getAge() {
    LocalDate now = LocalDate.now();
    LocalDate birthDate = this.birthday.getBirthdayOfYear(now.getYear());
    return birthDate.isAfter(now) ? now.getYear() - this.birthYear + 1
        : now.getYear() - this.birthYear;
  }

  public int getFakeAge() {
    return this.getAge() + 1;
  }

  public String getName() {
    return this.name;
  }
}


class Birthday {
  private int month;
  private int date;
  private boolean chineseCalendar;

  private Birthday(int month, int date, boolean chineseCalendar) {
    this.month = month;
    this.date = date;
    this.chineseCalendar = chineseCalendar;
  }

  public static Birthday from(int month, int date, boolean isChineseCalendar) {
    return new Birthday(month, date, isChineseCalendar);
  }

  public LocalDate getBirthdayOfYear(int year) {
    return ChineseCalendar.from(year, this.month, this.date, this.chineseCalendar);
  }
}
