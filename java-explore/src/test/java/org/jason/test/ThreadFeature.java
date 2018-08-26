package org.jason.test;

import org.junit.Test;

public class ThreadFeature {
  
  @Test
  public void threadTest() throws Throwable {
    Thread athread = new Thread ("current thread test"){
      public void run(){
        System.out.println("thread has finished running");
        System.out.println("Current thread name is: " + Thread.currentThread().getName());
      };
    };
    athread.start();
    System.out.println("Current thread name is: " + Thread.currentThread().getName());
    athread.interrupt();
  }


  @Test
  public void runnableTest() {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable is running");
        System.out.println("Current thread name is: " + Thread.currentThread().getName());
      }
    };
    Thread secondThread = new Thread(runnable);
    secondThread.setName("Runnable thread");
    System.out.println("Current thread name is: " + Thread.currentThread().getName());
    secondThread.start();
    secondThread.interrupt();
  }
}
