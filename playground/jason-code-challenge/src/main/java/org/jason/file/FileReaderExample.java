package org.jason.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderExample {


    /**
     * Common way
     *
     * @param fileName
     */
    public void readFile(String fileName) {
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = rd.readLine()) != null) {
                //
            }
        } catch (FileNotFoundException e) {
            System.out.println("Hello World");
        } catch (IOException e) {
            System.out.println("Hello World");
        }
        if (rd != null) {
            try {
                rd.close();
            } catch (IOException e) {
                System.out.println("Hello World");
            }
        }
    }

    /**
     * File reader using java 7.
     *
     * @param fileName
     */
    public void readFileJava7(String fileName) {
        try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while (rd.readLine() != null) {

            }
        } catch (IOException e) {
        }
    }

    /**
     * File reader using Nio.
     *
     * @param fileName
     */
    public void readFileSimpleNIO(String fileName) {
        try (BufferedReader bReader = Files.newBufferedReader(Paths.get(fileName))) {
            for (String line : Files.readAllLines(Paths.get(fileName))) {

            }
        } catch (IOException e) {
        }
    }

    /**
     * Most easy way to read file.
     *
     * @param fileName
     */
    public void readFileSimple(String fileName) {
        try {
            for (String line : Files.readAllLines(Paths.get(fileName))) {

            }
        } catch (IOException e) {
        }
    }

}
