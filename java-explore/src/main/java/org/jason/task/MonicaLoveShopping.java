package org.jason.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.commons.lang3.StringUtils;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@ThreadSafe
public class MonicaLoveShopping {

  public static void main(String[] args) throws InterruptedException, IOException {

    crawlMyDealAndReport();

  }

  public static List<String> crawlMyDealCategoryPages() throws IOException {

    return new MydealCategoryPageSpider().crawl();
  }

  public static void crawlMyDealAndReport() throws IOException, InterruptedException {

    List<String> categoryPages = crawlMyDealCategoryPages();

    List<String> itmePages = getItemsFromCategoryPage(categoryPages);

    report(itmePages);
  }

  public static void report(List<String> pageWithPrices) throws InterruptedException {

    for (String url : pageWithPrices) {
      System.out.println(url);
    }
  }

  public static List<String> getItemsFromCategoryPage(List<String> childCategories) throws InterruptedException {

    ExecutorService threadSerivice = Executors.newCachedThreadPool();

    List<Callable<List<String>>> callableTasks = new ArrayList<>();

    for (final String currentCategory : childCategories) {
      Callable<List<String>> callableTask = new MyDealCategoryTask(currentCategory).setToPrice(new BigDecimal(100));

      callableTasks.add(callableTask);
    }

    List<Future<List<String>>> itemPagesWithPrice = threadSerivice.invokeAll(callableTasks, 100, TimeUnit.SECONDS);

    List<String> result = itemPagesWithPrice.stream().flatMap(item -> {
      try {
        return item.get().stream();
      } catch (InterruptedException | ExecutionException e) {
        System.out.println(e.getMessage());
      }
      
      return null;
    }).distinct().collect(Collectors.toList());
    
    threadSerivice.shutdown();

    return result;
  }

  public static class MyDealCategoryTask implements Callable<List<String>> {

    private final String categoryPage;

    private static final String CATEGORY_SEARCH = "?sort=recommended&fromPrice=%d&toPrice=%d&tagId=2733&filter=0&filteredTagId=0&pageSize=5000&usr=1";

    public MyDealCategoryTask(String categoryPage) {
      this.categoryPage = categoryPage;
    }

    private BigDecimal fromPrice = BigDecimal.ZERO;

    public MyDealCategoryTask setFromPrice(BigDecimal fromPrice) {
      this.fromPrice = fromPrice;
      return this;
    }

    private BigDecimal toPrice = new BigDecimal(Integer.MAX_VALUE);

    public MyDealCategoryTask setToPrice(BigDecimal toPrice) {
      this.toPrice = toPrice;
      return this;
    }

    @Override
    public List<String> call() throws Exception {

      String categoryToSearch = getChildCategoryPageUrlWithPriceFilter();

      List<String> items = crawlCategoryForItems(categoryToSearch);

      return items;
    }

    private String getChildCategoryPageUrlWithPriceFilter() {
      String searchStr = String.format(CATEGORY_SEARCH, fromPrice.intValue(), toPrice.intValue());

      return this.categoryPage + searchStr;
    }

    private List<String> crawlCategoryForItems(String categoryPage) throws IOException {

      Document categoryPageDoc = WebCrawlUtil.crawlPage(categoryPage);

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
          items.add(MydealCategoryPageSpider.MY_DEAL_MAIN + itemUrl);
        }
      }

      return items;
    }
  }

  public static Map<String, Integer> sortMap(Map<String, Integer> sourceMap) {
    Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        Integer v1 = e1.getValue();
        Integer v2 = e2.getValue();
        return v1.compareTo(v2);
      }
    };

    // Sort method needs a List, so let's first convert Set to List in Java
    List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(sourceMap.entrySet());

    // sorting HashMap by values using comparator
    Collections.sort(listOfEntries, valueComparator);

    LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

    // copying entries from List to Map
    for (Entry<String, Integer> entry : listOfEntries) {
      sortedByValue.put(entry.getKey(), entry.getValue());
    }

    return sortedByValue;
  }

  public static interface PageSpider {
    public List<String> crawl() throws IOException;
  }

  /**
   * This Spider will get all child category page URL
   * 
   * @author jason
   *
   */
  public static class MydealCategoryPageSpider implements PageSpider {

    public static final String MY_DEAL_MAIN = "https://www.mydeal.com.au";
    private static final String MY_DEAL_CATEGORIES = "https://www.mydeal.com.au/categories";

    private static final String MY_DEAL_CATEGORY_CLASS = ".category-panel-body .category-item";

    @Override
    public List<String> crawl() throws IOException {

      Document myDealCategoryPage = WebCrawlUtil.crawlPage(MY_DEAL_CATEGORIES);

      Elements categories = myDealCategoryPage.select(MY_DEAL_CATEGORY_CLASS);

      List<String> categoryList = categories.stream().map(item -> item.child(0).getElementsByAttribute("href").attr("href")).collect(Collectors.toList());

      return categoryList.stream().map(item -> MY_DEAL_MAIN + "/" + item).collect(Collectors.toList());

    }

  }

  public static enum WebSite {
    YeeYi, MyDeal;
  }
}
