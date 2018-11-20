package org.jason.renting;

import org.apache.commons.lang3.StringUtils;

public class RentingDataModel {

  private Long price;

  private String source;

  private String rentingStyle;

  private String houseStyle;

  private String address;

  private String shortDescription;

  private String releaseTimeToNow;

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
    this.price = StringUtils.isNumeric(price) ? Long.parseLong(price) : null;
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

  public RentingDataModel releaseTime(String releaseTimeToNow) {
    this.releaseTimeToNow = releaseTimeToNow;
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

  public Long getPrice() {
    return price;
  }

  public String getReleaseTimeToNow() {
    return releaseTimeToNow;
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

    StringBuilder builder = new StringBuilder();
    builder.append("租金: $").append(price);
    builder.append(" 发布时间: ").append(releaseTimeToNow);
    builder.append(" 户型: ").append(houseStyle);
    builder.append(" 描述: ").append(shortDescription);
    builder.append(" 地址： ").append(address);
    return builder.toString();
  }

}
