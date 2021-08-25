package org.jason.olympics.model;

public class Athlete {
    private String fullName;
    private String gender;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    public String getResidenceCountry() {
        return residenceCountry;
    }

    public void setResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
    }

    public String getResidencePlace() {
        return residencePlace;
    }

    public void setResidencePlace(String residencePlace) {
        this.residencePlace = residencePlace;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getAttendedSport() {
        return attendedSport;
    }

    public void setAttendedSport(String attendedSport) {
        this.attendedSport = attendedSport;
    }

    private String dateOfBirth;
    private String passportCountry;
    private String residenceCountry;
    private String residencePlace;
    private String birthCountry;
    private String birthPlace;
    private Float height;
    private String attendedSport;


}
