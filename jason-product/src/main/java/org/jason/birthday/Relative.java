package org.jason.birthday;

import org.jason.finalclass.ChineseCalendar;

public enum Relative {
  MOTHER("妈妈", ChineseCalendar.fromLunar(1957, 8, 5)), 
  FATHER("爸爸", ChineseCalendar.fromLunar(1959, 12, 9)), 
  GRANDMA("奶奶", ChineseCalendar.fromLunar(1929, 10, 12));

  private Person person;

  private Relative(String name, ChineseCalendar lunarBirthday) {
    this.person = new Person(name, lunarBirthday);
  }

  public String getName() {
    return this.person.getName();
  }

  public Birthday getBirthday() {
    return this.person.getBirthday();
  }
}
