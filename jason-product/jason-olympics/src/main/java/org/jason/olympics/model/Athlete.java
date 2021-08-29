package org.jason.olympics.model;

import org.apache.commons.lang3.StringUtils;
import org.jason.util.JasonStringUtil;

public class Athlete {
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String passportCountry;
    private String residenceCountry;
    private String residencePlace;
    private String birthCountry;
    private String birthPlace;
    private Float height;
    private String attendedSport;

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

    public String toInsertSQLString(){
        StringBuilder insertSQLBuilder = new StringBuilder();
        insertSQLBuilder.append("INSERT INTO olympic_athlete(full_name,date_of_birth,gender,passport_country,residence_country,birth_country,height,birth_place,residence_place,attended_sport)");
        insertSQLBuilder.append(" values(");
        insertSQLBuilder.append(JasonStringUtil.quoted(fullName)).append(",");
        insertSQLBuilder.append(JasonStringUtil.quoted(dateOfBirth)).append(",");
        insertSQLBuilder.append(JasonStringUtil.quoted(gender)).append(",");
        insertSQLBuilder.append(JasonStringUtil.quoted(passportCountry)).append(",");
        residenceCountry = StringUtils.isBlank(residenceCountry) ? null : JasonStringUtil.quoted(residenceCountry);
        insertSQLBuilder.append(residenceCountry).append(",");
        birthCountry = StringUtils.isBlank(birthCountry) ? null : JasonStringUtil.quoted(birthCountry);
        insertSQLBuilder.append(birthCountry).append(",");
        height = height == null ? 0 : height;
        insertSQLBuilder.append(height).append(",");
        birthPlace = StringUtils.isBlank(birthPlace) ? "" : JasonStringUtil.quoted(birthPlace);
        insertSQLBuilder.append(birthPlace).append(",");
        residencePlace = StringUtils.isBlank(residencePlace) ? null : JasonStringUtil.quoted(residencePlace);
        insertSQLBuilder.append(residencePlace).append(",");
        attendedSport = StringUtils.isBlank(attendedSport) ? "" : attendedSport;
        insertSQLBuilder.append(JasonStringUtil.quoted(attendedSport)).append(");");

        return insertSQLBuilder.toString();
    }


}
