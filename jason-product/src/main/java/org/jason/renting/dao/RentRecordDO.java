package org.jason.renting.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RentRecord")
public class RentRecordDO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "price", nullable = false)
  private Long price;

  @Column(name = "source_type", nullable = false)
  private String sourceType;

  @Column(name = "rent_type", nullable = false)
  private String rentType;

  @Column(name = "house_type", nullable = false)
  private String houseType;

  @Column(name = "district", nullable = false)
  private String district;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "page_url", nullable = false)
  private String pageUrl;

  @Column(name = "page_content", nullable = false)
  private String pageContent;

  @Column(name = "release_date", nullable = false)
  private Date releaseDate;

  @Column(name = "gender_limit", nullable = false)
  private String genderLimit;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  public String getRentType() {
    return rentType;
  }

  public void setRentType(String rentType) {
    this.rentType = rentType;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPageUrl() {
    return pageUrl;
  }

  public void setPageUrl(String pageUrl) {
    this.pageUrl = pageUrl;
  }

  public String getPageContent() {
    return pageContent;
  }

  public void setPageContent(String pageContent) {
    this.pageContent = pageContent;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDateTime(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getGenderLimit() {
    return genderLimit;
  }

  public void setGenderLimit(String genderLimit) {
    this.genderLimit = genderLimit;
  }
  
}


