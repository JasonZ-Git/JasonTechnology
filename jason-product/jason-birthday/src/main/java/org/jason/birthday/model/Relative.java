package org.jason.birthday.model;

public enum Relative {
    MOTHER("妈妈", 1900, 8, 5, Person.CalendarType.Lunar),
    FATHER("爸爸", 1900, 12, 9, Person.CalendarType.Lunar),
    GRANDMA("奶奶", 1900, 10, 12, Person.CalendarType.Lunar),
    CHENGSEN("成森", 1900, 1, 18, Person.CalendarType.Lunar),
    CHENGHUI("成慧", 1900, 1, 1, Person.CalendarType.Lunar),
    HAORAN("浩然", 2000, 10, 12, Person.CalendarType.Solar), // Not confirmed
    GUORAN("果然", 2000, 04, 22, Person.CalendarType.Solar); // Not confirmed

    private Person person;

    private Relative(String name, int year, int month, int date, Person.CalendarType type) {
        this.person = type == Person.CalendarType.Lunar ? Person.ofLunar(name, year, month, date) : Person.ofSolar(name, year, month, date);
    }

    public String getName() {
        return this.person.getName();
    }

    public Person.Birthday getBirthday() {
        return this.person.getBirthday();
    }

    @Override
    public String toString(){
        return person.toString();
    }
}
