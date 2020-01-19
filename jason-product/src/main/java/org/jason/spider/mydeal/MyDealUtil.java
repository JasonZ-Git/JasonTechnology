package org.jason.spider.mydeal;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.apache.log4j.Logger;
import org.jason.spider.PageSpider;
import org.jason.util.Requirements;

@ThreadSafe
public class MyDealUtil {

  private static final PageSpider<String> SPIDER = new MyDealCategoryPageSpider();

  public static final String MY_DEAL_MAIN = "https://www.mydeal.com.au";
  public static final String MY_DEAL_CATEGORIES = MY_DEAL_MAIN + "/categories";
  
  public static final int DEFAULT_THREAD_NUMBER = 100;

  private static final Logger logger = Logger.getLogger(MyDealUtil.class);

  private MyDealUtil() throws Exception {
    throw new Exception("Init not allowed for utiliy class");
  }

  public static List<String> getPages() {

    try {
      List<String> categoryPages = crawlMyDealCategoryPages();

      return getItemsFromCategoryPage(categoryPages, null, null);

    } catch (Exception e) {
      logger.error(e);
    }

    return null;
  }
  
  public static Integer getPages(CompletionService<List<String>> completionService) {
    List<String> categoryPages = null;
    try {
      categoryPages = crawlMyDealCategoryPages();
      
      submitCategoryTasks(categoryPages, completionService);
      
    } catch (InterruptedException | IOException e) {
      logger.error(e);
    }
    
    return categoryPages.size();    
  }

  public static List<String> getPages(@Nonnull Integer priceLimt) {

    Requirements.requireTrue(priceLimt >= 1L);

    List<String> categoryPages;
    try {
      categoryPages = crawlMyDealCategoryPages();
      return getItemsFromCategoryPage(categoryPages, null, priceLimt);
    } catch (Exception e) {
      logger.error(e);
    }

    return null;
  }

  public static List<String> getPages(@Nonnull ExecutorService executorService) {

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

  private static List<String> getItemsFromCategoryPage(@Nonnull List<String> childCategories, @Nullable ExecutorService executorService, @Nullable Integer priceLimt) throws InterruptedException, ExecutionException {

    ExecutorService threadSerivice = executorService == null ? Executors.newFixedThreadPool(100) : executorService;

    List<MyDealCategoryTask> callableTasks = constructTasks(childCategories, priceLimt);

    List<Future<List<String>>> itemPagesWithPrice = threadSerivice.invokeAll(callableTasks);

    List<String> result = new ArrayList<>();
    for (Future<List<String>> current : itemPagesWithPrice){
      result.addAll(current.get());
    }

    threadSerivice.shutdown();

    return result;
  }
  
  private static void submitCategoryTasks(@Nonnull List<String> childCategories, @Nullable CompletionService<List<String>> executorService) throws InterruptedException {
    
    List<MyDealCategoryTask> callableTasks = constructTasks(childCategories);
    
    callableTasks.stream().forEach(item -> executorService.submit(item));
  }
  
  private static List<MyDealCategoryTask> constructTasks (List<String> childCategories){
    return constructTasks(childCategories, null);
  }
  
  private static List<MyDealCategoryTask> constructTasks (List<String> childCategories, Integer priceLimit){
    List<MyDealCategoryTask> callableTasks = new ArrayList<>();

    for (final String currentCategory : childCategories) {
      MyDealCategoryTask callableTask = new MyDealCategoryTask(currentCategory);
      
      if (priceLimit != null) callableTask.setToPrice(new BigDecimal(priceLimit));

      callableTasks.add(callableTask);
    }
    
    return callableTasks;
  }
  
}
