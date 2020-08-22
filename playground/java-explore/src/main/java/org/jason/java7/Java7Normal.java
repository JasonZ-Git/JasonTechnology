package org.jason.java7;

import java.util.Scanner;

public class Java7Normal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = (int) (Math.random() * 10);
        int guess;
        do {
            System.out.print(" 猜数字( 0 ~ 9 ) :");
            guess = scanner.nextInt();
        } while (guess != number);
        System.out.println(" 猜中了 ...XD");
        scanner.close();
    }

}

