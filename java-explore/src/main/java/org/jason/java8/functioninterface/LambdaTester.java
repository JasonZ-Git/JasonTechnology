package org.jason.java8.functioninterface;

public class LambdaTester {
    public static void main(String[] args) {

    }

    private static void simpleTest1() {
        GreetingService GreetingService = message -> System.out.println(message);
        Runnable runnable = () -> {
        };

        Comparable comparable = (b) -> {
            return 0;
        };
    }
}


interface GreetingService {
    void sayMessage(String message);
}
