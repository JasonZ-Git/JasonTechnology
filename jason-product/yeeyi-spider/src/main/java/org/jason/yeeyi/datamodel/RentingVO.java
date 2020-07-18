package org.jason.yeeyi.datamodel;

import org.apache.commons.lang3.StringUtils;

import java.util.Formatter;
import java.util.Locale;

public class RentingVO {

    private Long price;

    private String source;

    private String rentingStyle;

    private String houseStyle;

    private String address;

    private String shortDescription;

    private String detailLink;

    private String releaseTime;

    private Gender genderRestriction;

    private static String JUST_NOW = "刚刚";
    private static String MINUTES_AGO = "分钟前";
    private static String HOURS_AGO = "小时前";
    private static String DAYS_AGO = "天前";

    public RentingVO() {
    }

    public static RentingVO build() {
        return new RentingVO();
    }

    public RentingVO source(String source) {
        this.source = source;
        return this;
    }

    public RentingVO rentingStyle(String rentingStyle) {
        this.rentingStyle = rentingStyle;
        return this;
    }

    public RentingVO price(String price) {
        if (price == null) {
            this.price = 0L;
        } else {
            this.price = StringUtils.isNumeric(price) ? Long.parseLong(price) : 0L;
        }

        return this;
    }

    public RentingVO shortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public RentingVO houseStyle(String houseStyle) {
        this.houseStyle = houseStyle.replace("户型：", "");
        return this;
    }

    public RentingVO address(String address) {
        this.address = address.replace("地址：", "");
        return this;
    }

    public RentingVO link(String link) {
        this.detailLink = link;
        return this;
    }

    public RentingVO releaseTime(String releaseTimeToNow) {
        this.releaseTime = releaseTimeToNow;
        return this;
    }

    public RentingVO genderLimit(String genderLimit) {
        switch (genderLimit) {
            case "男女不限":
                this.genderRestriction = Gender.NONE;
                break;
            case "限女生":
                this.genderRestriction = Gender.FEMALE;
                break;
            case "限男生":
                this.genderRestriction = Gender.MALE;
                break;
            default:
                this.genderRestriction = Gender.NONE;
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
        return detailLink;
    }

    public Long getPrice() {
        return price;
    }

    public String getReleaseTimeToNow() {
        return releaseTime;
    }

    public Gender getGenderLimit() {
        return genderRestriction;
    }

    public boolean isWithinDays(int days) {
        if (releaseTime.contains(JUST_NOW) || releaseTime.contains(MINUTES_AGO) || releaseTime.contains(HOURS_AGO)) {
            return true;
        }

        if (releaseTime.contains(DAYS_AGO)) {
            String daysParsed = releaseTime.substring(0, releaseTime.indexOf(DAYS_AGO));
            return Long.parseLong(daysParsed) <= days;
        }

        return false;
    }

    public static RentingVO from(RentRecordDO rentingDO) {
        RentingVO vo = new RentingVO();
        vo.address(rentingDO.getAddress()).link(rentingDO.getPageUrl()).houseStyle(rentingDO.getHouseType()).price(rentingDO.getPrice().toString()).shortDescription(rentingDO.getTitle()).releaseTime(rentingDO.getReleaseDate().toString());

        return vo;
    }

    @Override
    public String toString() {
        Formatter formater = new Formatter(Locale.SIMPLIFIED_CHINESE);
        formater.format("%s: %-8d", "租金", price);
        formater.format("%s: %-10s", "发布时间", releaseTime);
        formater.format("%s: %-12s", "户型", houseStyle);
        formater.format("%s: %-55s", "描述", shortDescription);
        formater.format("%s: %-35s", "地址", address);
        formater.format("%s: %-30s", "详情", detailLink);
        String result = formater.toString();
        formater.close();

        return result;
    }

    public static enum Gender {
        MALE, FEMALE, NONE, ALL;
    }

}
