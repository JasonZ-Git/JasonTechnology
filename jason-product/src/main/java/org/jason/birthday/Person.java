package org.jason.birthday;

import java.time.LocalDate;

import org.jason.finalclass.ChineseCalendar;

public class Person {
  private String name;
  private Birthday birthday;
  private int birthYear;

  private Person(String name, int birthYear, Birthday birthday) {
    this.name = name;
    this.birthYear = birthYear;
    this.birthday = birthday;
  }

  public Person(String name, ChineseCalendar lunarBirthday) {
    this(name, lunarBirthday.get(ChineseCalendar.CHINESE_YEAR), Birthday.fromLunar(lunarBirthday));
  }

  public Birthday getBirthday() {
    return this.birthday;
  }

  public static int getAge(Person person) {
    LocalDate now = LocalDate.now();
    LocalDate birthDate = person.birthday.getBirthdayOfYear(now.getYear());
    return birthDate.isAfter(now) ? now.getYear() - person.birthYear + 1
        : now.getYear() - person.birthYear;
  }

  public static int getFakeAge(Person person) {
    return getAge(person) + 1;
  }

  public String getName() {
    return this.name;
  }
  
  public int getBirthYear() {
    return this.birthYear;
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
  
  public static Birthday fromLunar(ChineseCalendar lunarBirthday) {
	  return new Birthday(lunarBirthday.get(ChineseCalendar.CHINESE_MONTH),
			              lunarBirthday.get(ChineseCalendar.CHINESE_DATE),
					      true);
  }

  public LocalDate getBirthdayOfYear(int year) {
    return ChineseCalendar.from(year, this.month, this.date, this.chineseCalendar);
  }
}
