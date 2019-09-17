package org.jason.renting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class YeeyiUtil {
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

  private YeeyiUtil() {
    throw new AssertionError("Not allowed");
  }

  public static List<RentingVO> toDataModel(Document document) {
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
