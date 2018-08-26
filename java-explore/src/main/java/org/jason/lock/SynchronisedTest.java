package org.jason.lock;

public class SynchronisedTest {
  public  int inc = 0;//此处没有了volatile

  public synchronized void increase() {//此处添加了synchronized，与使用同步代码块效果一样
      inc++;
  }

  public static void main(String[] args) {
      final SynchronisedTest test = new SynchronisedTest();
      for(int i=0;i<10;i++){
          new Thread(){
              public void run() {
                  for(int j=0;j<1000;j++)
                      test.increase();
              };
          }.start();
      }

      while(Thread.activeCount()>1)  //保证前面的线程都执行完
          Thread.yield();
      System.out.println(test.inc);
  }
}
