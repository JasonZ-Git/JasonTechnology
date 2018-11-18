package org.jason.util.algorithm;

public class FizzBuzzUtil {

  /**
   * Generate a String.
   * 
   * @param i
   * @return
   */
  public static String getFizzBuzz(int i) {
    String result = "";
    
    result = ((i%3 == 0) ? "Fizz" : "") + (((i%5 == 0) ? result + "Buzz" : result));
    
    return result.equals("") ? String.valueOf(i) : result;
  }

}
