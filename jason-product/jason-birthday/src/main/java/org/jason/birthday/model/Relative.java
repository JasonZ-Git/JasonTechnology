package org.jason.birthday.model;

public enum Relative {
    MOTHER("妈妈", 1957, 8, 5, Person.CalendarType.Lunar),
    FATHER("爸爸", 1959, 12, 9, Person.CalendarType.Lunar),
    GRANDMA("奶奶", 1929, 10, 12, Person.CalendarType.Lunar),
    Chengsen("Chengsen", 1984, 1, 18, Person.CalendarType.Lunar),
    Chenghui("Chenghui", 1982, 1, 1, Person.CalendarType.Lunar), // Not confirmed
    NEPHEW_HAORAN("Haoran", 2011, 06, 24, Person.CalendarType.Solar),
    NEPHEW_GUORAN("Guoran", 2017, 04, 20, Person.CalendarType.Solar);

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
