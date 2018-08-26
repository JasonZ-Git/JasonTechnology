package org.jason.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
  public AtomicInteger inc = new AtomicInteger();// 将inc变成原子性操作

  public void increase() {
    inc.getAndIncrement();
  }

  public static void main(String[] args) {
    final AtomicTest test = new AtomicTest();
    for (int i = 0; i < 10; i++) {
      new Thread() {
        public void run() {
          for (int j = 0; j < 1000; j++)
            test.increase();
        };
      }.start();
    }

    while (Thread.activeCount() > 1) // 保证前面的线程都执行完
      Thread.yield();
    System.out.println(test.inc);
  }
}
