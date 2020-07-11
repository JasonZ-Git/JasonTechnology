package org.jason.spider.yeeyi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class YeeyiCriteria {
    private static final String DISCTRICT_KEY = "rsuburb1=";
    private String district;
    // 1整租 2转租 3单间 4客厅 5床位 6其它
    private String rentType = "renttype1=";
    // 1: 100$/week以下; 2: 100-150$/week; 3: 150-200$/week; 4: 200-300$/week; 5: 300-500$/week; 6:
    // 500-1000$/week
    private String price = "rents=";
    // 1Apartment 2小区 |Unit 3平房|Bungalow 4别墅|Hous
    private String houseType = "rhousetype1";

    private YeeyiCriteria() {
    }

    public static YeeyiCriteria build() {
        return new YeeyiCriteria();
    }

    public YeeyiCriteria district(String district) {
        this.district = district;
        return this;
    }

    public YeeyiCriteria rentType(String type) {
        this.rentType += type;
        return this;
    }

    public String getSearchCriteria() {
        try {
            return DISCTRICT_KEY + URLEncoder.encode(this.district, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
