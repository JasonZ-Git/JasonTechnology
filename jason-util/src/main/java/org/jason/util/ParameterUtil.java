package org.jason.util;

public class ParameterUtil {

  private ParameterUtil() {}
  
  public static void requireTrue(boolean parameter) {
    requireTrue(parameter, "");
  }
  
  public static void requireTrue(boolean parameter, String message) {
    if (!parameter){
      throw new IllegalArgumentException(message);
    }
  }
  
}
