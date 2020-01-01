package org.jason.spider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This Spider will get all child category page URLs
 * 
 * @author Jason Zhang
 *
 */
public class MyDealCategoryPageSpider implements PageSpider<String> {

  private static final String MY_DEAL_CATEGORY_CLASS = ".category-panel-body .category-item";

  @Override
  public List<String> crawl() throws IOException {

    Document myDealCategoryPage = WebCrawlUtil.crawlPage(MyDealUtil.MY_DEAL_CATEGORIES);

    Elements categories = myDealCategoryPage.select(MY_DEAL_CATEGORY_CLASS);

    List<String> categoryList = categories.stream().map(item -> item.child(0).getElementsByAttribute("href").attr("href")).collect(Collectors.toList());

    return categoryList.stream().map(item -> MyDealUtil.MY_DEAL_MAIN + "/" + item).collect(Collectors.toList());

  }

}
