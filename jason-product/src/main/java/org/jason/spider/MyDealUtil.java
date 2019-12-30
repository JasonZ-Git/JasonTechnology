package org.jason.spider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.log4j.Logger;
import org.jason.util.ObjectsUtil;

@ThreadSafe
public class MyDealUtil {

  private static final PageSpider<String> SPIDER = new MyDealCategoryPageSpider();

  public static final String MY_DEAL_MAIN = "https://www.mydeal.com.au";
  public static final String MY_DEAL_CATEGORIES = MY_DEAL_MAIN + "/categories";

  private static final Logger logger = Logger.getLogger(MyDealUtil.class);

  private MyDealUtil() throws Exception {
    throw new Exception("Init not allowed for utiliy class");
  }

  public static List<String> getItemPages() {

    try {
      List<String> categoryPages = crawlMyDealCategoryPages();

      return getItemsFromCategoryPage(categoryPages, null, null);

    } catch (Exception e) {
      logger.error(e);
    }

    return null;
  }

  public static List<String> getItemPages(@Nonnull Integer priceLimt) {

    ObjectsUtil.requireTrue(priceLimt >= 1L);

    List<String> categoryPages;
    try {
      categoryPages = crawlMyDealCategoryPages();
      return getItemsFromCategoryPage(categoryPages, null, priceLimt);
    } catch (Exception e) {
      logger.error(e);
    }

    return null;
  }

  public static List<String> getItemPages(@Nonnull ExecutorService executorService) {

    Objects.requireNonNull(executorService);

    List<String> categoryPages;
    try {
      categoryPages = crawlMyDealCategoryPages();
      return getItemsFromCategoryPage(categoryPages, executorService, null);

    } catch (Exception e) {
      logger.error(e);
    }

    return null;
  }

  private static List<String> crawlMyDealCategoryPages() throws IOException {
    return SPIDER.crawl();
  }

  private static List<String> getItemsFromCategoryPage(@Nonnull List<String> childCategories, @Nullable ExecutorService executorService, @Nullable Integer priceLimt) throws InterruptedException {

    ExecutorService threadSerivice = executorService == null ? Executors.newFixedThreadPool(100) : executorService;

    List<Callable<List<String>>> callableTasks = new ArrayList<>();

    for (final String currentCategory : childCategories) {
      MyDealCategoryTask callableTask = new MyDealCategoryTask(currentCategory);
      if (priceLimt != null)
        callableTask.setToPrice(new BigDecimal(priceLimt));

      callableTasks.add(callableTask);
    }

    List<Future<List<String>>> itemPagesWithPrice = threadSerivice.invokeAll(callableTasks);// , 100, TimeUnit.SECONDS);

    List<String> result = itemPagesWithPrice.stream().flatMap(item -> {
      try {
        return item.get().stream();
      } catch (Exception e) {
        // logger.error(e);
      }

      return null;
    }).distinct().collect(Collectors.toList());

    threadSerivice.shutdown();

    return result;
  }
}
