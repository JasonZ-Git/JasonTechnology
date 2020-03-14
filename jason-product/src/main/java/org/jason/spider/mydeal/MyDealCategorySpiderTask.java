package org.jason.spider.mydeal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.jason.util.Requirements;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class is specific designed for crawling one category page of MyDeal
 * 
 * @author Jason Zhang
 *
 */
public class MyDealCategorySpiderTask implements Callable<List<String>> {

  private static final String CATEGORY_SEARCH = "?sort=price_low_to_high&fromPrice=%d&toPrice=%d&pageSize=%d";

  private final String categoryPage;
  
  public MyDealCategorySpiderTask(String categoryPage) {
    
    Objects.requireNonNull(categoryPage);
    
    Requirements.requireTrue(categoryPage.startsWith(MyDealUtil.MY_DEAL_MAIN));
    
    this.categoryPage = categoryPage;
  }
  
  // CANNOT be changed for now
  private final int PAGE_SIZE = 5000;

  @Nonnull
  private BigDecimal fromPrice = BigDecimal.ZERO;
  public MyDealCategorySpiderTask setFromPrice(BigDecimal fromPrice) {
    this.fromPrice = fromPrice;
    return this;
  }

  @Nonnull
  private BigDecimal toPrice = new BigDecimal(Integer.MAX_VALUE);
  public MyDealCategorySpiderTask setToPrice(BigDecimal toPrice) {
    this.toPrice = toPrice;
    return this;
  }

  @Override
  public List<String> call() throws Exception {

    String categoryPageToSearch = getCategoryPageWithFilter();

    List<String> itemPages = crawlCategoryForItems(categoryPageToSearch);

    return itemPages;
  }

  private String getCategoryPageWithFilter() {
    String searchStr = String.format(CATEGORY_SEARCH, this.fromPrice.intValue(), this.toPrice.intValue(), PAGE_SIZE);

    return this.categoryPage + searchStr;
  }

  private List<String> crawlCategoryForItems(String categoryPage) throws IOException {

    Document categoryPageDoc = SpiderUtil.crawlPage(categoryPage);

    Elements gridItems = categoryPageDoc.select(".grid-item");

    List<String> items = new ArrayList<>();

    for (Element currentGrid : gridItems) {

      Elements priceEles = currentGrid.select(".grid-item .numericSpan .mainPriceSpan");

      if (priceEles.isEmpty() || priceEles.size() != 1)
        continue;

      Element currentPrice = priceEles.get(0);

      String price = currentPrice.text();
      if (StringUtils.isNumeric(price)) {
        String itemUrl = currentGrid.select(".ellipsis a[href]").get(0).attr("href");
        items.add(MyDealUtil.MY_DEAL_MAIN + "/" + itemUrl);
      }
    }

    return items;
  }
}