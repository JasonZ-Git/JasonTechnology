package org.jason.spider.yeeyi.application;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.mail.EmailException;
import org.jason.spider.mydeal.MyDealUtil;


public class MydealApplication {

  public static void main(String[] args) throws IOException, InterruptedException, EmailException {

    executorPerformanceTest();

  }

  public static void executorPerformanceTest() {
    ExecutorService executor = null;
    StopWatch stopWatch = null;
    List<String> itemPages = null;
    int threadPoolSize = 1;
    
    // -- Test single thread pool --
    stopWatch = StopWatch.createStarted();

    executor = Executors.newSingleThreadExecutor();
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("Total items are: %s%n", itemPages.size());
    System.out.printf("With %s, the run time is %s seconds %n%n", "single thread pool", stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();
    
    // -- Test 10 threadS --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 10;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    // -- Test 20 threadS --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 20;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    
    // -- Test 50 threadS --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 50;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    
    // -- Test 100 threadS --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 100;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    // -- Test 200 threads --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 200;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    // -- Test 500 threads --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 500;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    // -- Test 1000 threads --
    stopWatch = StopWatch.createStarted();

    threadPoolSize = 1000;
    executor = Executors.newFixedThreadPool(threadPoolSize);
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %d threads, the run time is %s seconds %n%n", threadPoolSize, stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();

    
    // -- Test cached thread pool, there are 1300 threads --
    stopWatch = StopWatch.createStarted();

    executor = Executors.newCachedThreadPool();
    itemPages = MyDealUtil.getPages(executor);

    stopWatch.stop();

    System.out.printf("With %s, the run time is %s seconds %n%n", "cached thread pool(1300 threads in this test case)", stopWatch.getTime(TimeUnit.SECONDS));
    executor.shutdown();


    /**
     * This is the result: Total items are: 137682 With 100 Fixed thread Pool, the run time is 32 seconds
        
        Total items are: 137759
                
        With single thread pool, the run time is 351 seconds 
        
        With 10 threads, the run time is 47 seconds 
        
        With 20 threads, the run time is 31 seconds 
        
        With 50 threads, the run time is 26 seconds 
        
        With 100 threads, the run time is 23 seconds 
        
        With 200 threads, the run time is 28 seconds 
        
        With 500 threads, the run time is 27 seconds 
        
        With 1000 threads, the run time is 80 seconds 
        
        With cached thread pool(1300 threads in this test case), the run time is 59 seconds 
     */
  }
}
