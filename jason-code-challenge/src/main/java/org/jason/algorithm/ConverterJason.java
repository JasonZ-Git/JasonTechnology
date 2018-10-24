package org.jason.algorithm;

/**
 * Convert a String to 7 based string.
 * 
 * @author jason.zhang
 */
public class ConverterJason {

  public static void main(String[] args) {
    long input = 100;
    System.out.println("intput of " + input + ", output is " + convert(input));
  }


  public static String convert(long input) {
    if (input == 0) {
      return "0";
    }

    final char[] encodingPattern = {'0', 'a', 't', 'l', 's', 'i', 'n'};

    String output = "";

    while (input / 7 >= 1) {
      output = encodingPattern[(int) input % 7] + output;
      input = input / 7;
    }

    output = encodingPattern[(int) input % 7] + output;

    return output;
  }

}
