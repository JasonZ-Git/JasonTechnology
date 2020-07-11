package org.jason.java7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class JavaStreamStudy {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new File("src/main/resources/outputFile.txt"));
        System.setOut(printStream);
        System.out.println("HelloWorld");
    }
}
