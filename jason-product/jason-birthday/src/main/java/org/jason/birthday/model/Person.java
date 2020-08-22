package org.jason.birthday.model;

public class Person {
    private String name;
    private Birthday birthday;

    private Person(String name, Birthday birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public static Person of(String name, Birthday birthDate) {
        return new Person(name, birthDate);
    }

    public static Person ofSolar(String name, int year, int month, int date) {
        return new Person(name, Birthday.fromSolar(year,month,date));
    }

    public static Person ofLunar(String name, int year, int month, int date) {
        return new Person(name, Birthday.fromLunar(year,month,date));
    }

    public String getName() {
        return this.name;
    }

    public Birthday getBirthday(){
        return this.birthday;
    }

    @Override
    public String toString (){
        return String.format("%s: %s", name, birthday.toString());
    }

    public static enum CalendarType{
        Lunar("阴历"), Solar("阳历");

        private String type;

        CalendarType(String type){
            this.type = type;
        }

        public String getType(){return type;}
    }

    public static class Birthday {
        private int year;
        private int month;
        private int date;
        private CalendarType type;

        private Birthday(int year, int month, int date, CalendarType type) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.type = type;
        }

        public static Birthday fromSolar(int year, int month, int date) {
            return new Birthday(year, month, date, CalendarType.Solar);
        }

        public static Birthday fromLunar(int year, int month, int date) {
            return new Birthday(year, month, date, CalendarType.Lunar);
        }

        @Override
        public String toString(){
            return String.format("%s %s-%s-%s", type.getType(), year, month, date);
        }

    }
}




