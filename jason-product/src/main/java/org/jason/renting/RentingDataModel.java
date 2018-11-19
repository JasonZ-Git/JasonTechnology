package org.jason.renting;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RentingDataModel {

  private Long price;

  private String source;

  private String rentingStyle;

  private String houseStyle;

  private String address;

  private String shortDescription;
  
  private String releaseTimeToNow;

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
    this.houseStyle = houseStyle;
    return this;
  }

  public RentingDataModel address(String address) {
    this.address = address;
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

  public RentingDataModel releaseTime(String releaseTimeToNow) {
    this.releaseTimeToNow = releaseTimeToNow;
    return this;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
  }

}
