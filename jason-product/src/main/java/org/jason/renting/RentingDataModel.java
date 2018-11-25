package org.jason.renting;

import java.util.Formatter;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class RentingDataModel {

  private Long price;

  private String source;

  private String rentingStyle;

  private String houseStyle;

  private String address;

  private String shortDescription;

  private String link;

  private String releaseTimeToNow;

  private Gender genderLimit;

  private static String JUST_NOW = "刚刚";
  private static String MINUTES_AGO = "分钟前";
  private static String HOURS_AGO = "小时前";
  private static String DAYS_AGO = "天前";

  public RentingDataModel() {}

  public static RentingDataModel build() {
    return new RentingDataModel();
  }

  public RentingDataModel source(String source) {
    this.source = source;
    return this;
  }

  public RentingDataModel rentingStyle(String rentingStyle) {
    this.rentingStyle = rentingStyle;
    return this;
  }

  public RentingDataModel price(String price) {
    if (price == null) {
      this.price = 0L;
    } else {
      this.price = StringUtils.isNumeric(price) ? Long.parseLong(price) : 0L;
    }

    return this;
  }

  public RentingDataModel shortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
    return this;
  }

  public RentingDataModel houseStyle(String houseStyle) {
    this.houseStyle = houseStyle.replace("户型：", "");
    return this;
  }

  public RentingDataModel address(String address) {
    this.address = address.replace("地址：", "");
    return this;
  }

  public RentingDataModel link(String link) {
    this.link = link;
    return this;
  }

  public RentingDataModel releaseTime(String releaseTimeToNow) {
    this.releaseTimeToNow = releaseTimeToNow;
    return this;
  }

  public RentingDataModel genderLimit(String genderLimit) {
    switch (genderLimit) {
      case "男女不限":
        this.genderLimit = Gender.NONE;
        break;
      case "限女生":
        this.genderLimit = Gender.FEMALE;
        break;
      case "限男生":
        this.genderLimit = Gender.MALE;
        break;
      default:
        this.genderLimit = Gender.NONE;
        break;
    }

    return this;
  }

  public String getSource() {
    return source;
  }

  public String getRentingStyle() {
    return rentingStyle;
  }

  public String getHouseStyle() {
    return houseStyle;
  }

  public String getAddress() {
    return address;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getLink() {
    return link;
  }

  public Long getPrice() {
    return price;
  }

  public String getReleaseTimeToNow() {
    return releaseTimeToNow;
  }
  
  public Gender getGenderLimit() {
    return genderLimit;
  }

  public boolean isWithinDays(int days) {
    if (releaseTimeToNow.contains(JUST_NOW) || releaseTimeToNow.contains(MINUTES_AGO) || releaseTimeToNow.contains(HOURS_AGO)) {
      return true;
    }

    if (releaseTimeToNow.contains(DAYS_AGO)) {
      String daysParsed = releaseTimeToNow.substring(0, releaseTimeToNow.indexOf(DAYS_AGO));
      return Long.parseLong(daysParsed) <= days;
    }

    return false;
  }

  @Override
  public String toString() {
    Formatter formater = new Formatter(Locale.SIMPLIFIED_CHINESE);
    formater.format("%s: %-8d", "租金", price);
    formater.format("%s: %-10s", "发布时间", releaseTimeToNow);
    formater.format("%s: %-12s", "户型", houseStyle);
    formater.format("%s: %-55s", "描述", shortDescription);
    formater.format("%s: %-35s", "地址", address);
    formater.format("%s: %-30s", "详情", link);
    String result = formater.toString();
    formater.close();

    return result;
  }

  public static enum Gender {
    MALE, FEMALE, NONE, ALL;
  }

}
