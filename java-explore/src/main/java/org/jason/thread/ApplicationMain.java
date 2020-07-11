package org.jason.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApplicationMain {

    public static void main(String[] args) {
        thread1();
    }

    public static void thread1() {
        Thread thread1 = new Thread() {
            public void run() {
                System.out.println("Thread 2");
                ;

            }
        };

        thread1.start();

        Runnable runableT = new Runnable() {
            public void run() {
                System.out.println("Runnable test");
            }
        };

        Thread t = new Thread(runableT);
        t.start();

        Runnable runnableT2 = () -> {
            System.out.println("Java 8 lamda style");
        };

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> result = service.submit(() -> {
            System.out.println("Hello Executor");
            return "Hello Executor";
        });

        service.shutdown();


    }
}
