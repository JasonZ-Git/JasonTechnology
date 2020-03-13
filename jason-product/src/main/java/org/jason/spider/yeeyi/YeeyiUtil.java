package org.jason.spider.yeeyi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jason.spider.yeeyi.controller.RentingVO;
import org.jason.spider.yeeyi.dao.RentRecordDO;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class YeeyiUtil {
  
  public static final String YeeyiRentingURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1&page=1";
  
  private static final String ELEMENT_SELECTOR = "#qtcShow .qtc li";
  private static final String SHORT_DESC_SELECTOR = ".ptxt .zdtxt";
  private static final String SOURCE_SELECTOR = ".ptxt p span:nth-child(1)";
  private static final String RENTING_STYLE_SELECTOR = ".ptxt p span:nth-child(2)";
  private static final String HOUSE_STYLE_SELECTOR = ".ptxt p span:nth-child(3)";
  private static final String PRICE_SELECTOR = ".num .colorblod";
  private static final String RELEASE_TIME_SELECTOR = ".num p:nth-child(2)";
  private static final String ADDRESS_SELECTOR = ".ptxt p:nth-child(3)";
  private static final String LINK_SELECTOR = ".ptxt [href]";
  private static final String ADVERTISEMENT_SELECTOR = ".ptxt .pin";
  private static final String AD_KEYWORD = "置顶";
  private static final String HOUSE_AD = "0室0卫";
  private static final String NO_INFORMATION = "未找到符合条件的信息";

  private static final int DEFAULT_MAX = 30;
  private static final int DEFAULT_SHOW_DAYS = 2;
  private static final String DEFAULT_FILTER_OUT_WORD = "短租";

  private static final Logger logger =  LogManager.getLogger(YeeyiUtil.class);
  
  private YeeyiUtil() {
    throw new AssertionError("Not allowed");
  }
  
  public static Document readYeeyiRentingPage() {
    Document yeeyiRentPage =  null;
    
    try {
      yeeyiRentPage = SpiderUtil.crawlPage(YeeyiRentingURL);
    } catch (IOException e) {
      logger.error(e);
    }
    
    return yeeyiRentPage;
  }
  
  public static List<RentRecordDO> toRentingDO(Document document) {

    Elements elements = document.select(ELEMENT_SELECTOR);

    List<RentRecordDO> items = new ArrayList<>();
    for (Element current : elements) {
      if (current.text().equals(NO_INFORMATION))
        break;

      Element advertisement = current.selectFirst(ADVERTISEMENT_SELECTOR);
      if (advertisement != null && advertisement.text().equals(AD_KEYWORD)) {
        continue;
      }

      String shortDescription = current.selectFirst(SHORT_DESC_SELECTOR).text();
      String sourceType = current.selectFirst(SOURCE_SELECTOR).text();
      String rentType = current.selectFirst(RENTING_STYLE_SELECTOR).text();
      String houseType = current.selectFirst(HOUSE_STYLE_SELECTOR).text();
      String address = current.selectFirst(ADDRESS_SELECTOR).text();
      String price = current.selectFirst(PRICE_SELECTOR).text();
      String releaseTimeToNow = current.selectFirst(RELEASE_TIME_SELECTOR).text();
      String link = current.selectFirst(LINK_SELECTOR).attr("href");

      if (houseType == null || houseType.contains(HOUSE_AD)) {
        houseType = "";
      }

      RentRecordDO item = new RentRecordDO();
      item.setAddress(address);
      item.setDistrict("");
      item.setSourceType(getSourceType(sourceType));
      item.setRentType(getRentType(rentType));
      item.setHouseType(getHouseType(houseType));
      item.setGenderLimit("");
      item.setPageUrl(link);
      item.setPageContent("");
      item.setTitle(shortDescription);
      item.setPrice(getPrice(price));
      item.setReleaseDateTime(getReleaseDateTime(releaseTimeToNow));

      items.add(item);
    }
    return items;
  }

  private static Long getPrice(String price) {
    return Double.valueOf(price).longValue();
  }


  private static String getRentType(String rentType) {
    
    return rentType.replace("出租方式：", "").trim();
  }


  private static String getHouseType(String houseType) {
    return houseType.replace("户型：", "").trim();
  }

  private static String getSourceType(String sourceType) {
    return sourceType.replace("来源：", "").trim();
  }

  private static Date getReleaseDateTime(String releaseTimeToNow) {
    final String NOW = "刚刚";
    final String DAYS_AGO = "天前";
    final String HOURS_AGO = "小时前";
    final String MINUTES_GAO = "分钟前";
    
    Date now = new Date();
    
    Date result = null;
    
    Calendar current = Calendar.getInstance();
    current.setTime(now);
    if (StringUtils.isEmpty(releaseTimeToNow) || releaseTimeToNow.equals(NOW)) {
      result = now;
    } else if (releaseTimeToNow.endsWith(DAYS_AGO)) {
      int daysAgo = Integer.parseInt(releaseTimeToNow.replace(DAYS_AGO, ""));
      result = DateUtils.addDays(now, daysAgo * -1);
    } else if (releaseTimeToNow.endsWith(HOURS_AGO)) {
      int hoursAgo = Integer.parseInt(releaseTimeToNow.replace(HOURS_AGO, ""));
      result = DateUtils.addHours(now, hoursAgo * -1);
    } else if (releaseTimeToNow.endsWith(MINUTES_GAO)) {
      int minutesAgo = Integer.parseInt(releaseTimeToNow.replace(MINUTES_GAO, ""));
      result = DateUtils.addMinutes(now, minutesAgo * -1);
    }
      
    return result;
  }


  public static List<RentingVO> toRentingVO(Document document) {
    Elements elements = document.select(ELEMENT_SELECTOR);

    List<RentingVO> items = new ArrayList<>();
    for (Element current : elements) {
      if (current.text().equals(NO_INFORMATION))
        break;
      
      Element advertisement = current.selectFirst(ADVERTISEMENT_SELECTOR);
      if (advertisement != null && advertisement.text().equals(AD_KEYWORD)) {
        continue;
      }

      String shortDescription = current.selectFirst(SHORT_DESC_SELECTOR).text();
      String source = current.selectFirst(SOURCE_SELECTOR).text();
      String rentingStyle = current.selectFirst(RENTING_STYLE_SELECTOR).text();
      String houseStyle = current.selectFirst(HOUSE_STYLE_SELECTOR).text();
      String address = current.selectFirst(ADDRESS_SELECTOR).text();
      String price = current.selectFirst(PRICE_SELECTOR).text();
      String releaseTimeToNow = current.selectFirst(RELEASE_TIME_SELECTOR).text();
      String link = current.selectFirst(LINK_SELECTOR).attr("href");
      RentingVO model = RentingVO.build().source(source).price(price).link(link).releaseTime(releaseTimeToNow).rentingStyle(rentingStyle).houseStyle(houseStyle).address(address)
          .shortDescription(shortDescription);

      if (houseStyle != null && houseStyle.contains(HOUSE_AD)) {
        continue;
      }
      items.add(model);
    }
    return items;
  }

  public static List<RentingVO> defaultFilter(List<RentingVO> items) {
    Objects.requireNonNull(items);

    return filter(items, DEFAULT_FILTER_OUT_WORD, DEFAULT_SHOW_DAYS, DEFAULT_MAX);
  }

  public static List<RentingVO> filter(List<RentingVO> items, String filterOutTitleWord, int releaseDaysToNow, int maximum) {
    Objects.requireNonNull(items);

    return items.stream().filter(item -> !item.getShortDescription().contains(filterOutTitleWord)).filter(item -> item.isWithinDays(releaseDaysToNow)).limit(maximum).collect(Collectors.toList());
  }
}
