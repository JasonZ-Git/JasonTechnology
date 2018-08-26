package org.jason.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Java8Thread {
  public static void main(String[] args) throws Exception {
	  executorCallable();
  }

  public static void runnableExample() {
    Runnable case1 = () -> {
      String name = Thread.currentThread().getName();
      System.out.println(name);
    };

    case1.run();

    System.out.println("Case1 finishes!");

    Thread case2 = new Thread(case1);
    case2.start();
    System.out.println("Case2 finishes");
  }

  public static void threadExample() {
    Runnable runnable = () -> {
      try {
        String name = Thread.currentThread().getName();
        System.out.println("Foo " + name);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Bar " + name);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
  }

  public static void executorExample() {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> {
      System.out.println(Thread.currentThread().getName());
    });

    try {
      executor.shutdown();
      executor.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException ex) {
      System.err.println("Error shutting down thread");
    } finally {
      System.out.println(executor.isTerminated());
      executor.shutdownNow();
      System.out.println("Finished");
    }
  }

  public static void executorCallable() throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    Future<String> result = executor.submit(() -> {
      System.out.println("Thread starts");
      return "result of callable interface";
    });
    
    System.out.println("future done? " + result.isDone());

    System.out.println(result.get());
    
    System.out.println("future done? " + result.isDone());
    executor.shutdownNow();
  }
  
  public static void scheduledExecutorExample() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    Runnable task = () -> {
        try {
            TimeUnit.SECONDS.sleep(0);
            System.out.println("Scheduling: " + System.nanoTime());
        }
        catch (InterruptedException e) {
            System.err.println("task interrupted");
        }
    };

    executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
  }
}
