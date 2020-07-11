package org.jason.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public int inc = 0;//此处没有了volatile
    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();//此处添加了lock();
        try {
            inc++;
        } finally {
            lock.unlock();//解锁unlock
        }
    }

    public static void main(String[] args) {
        final LockTest test = new LockTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}