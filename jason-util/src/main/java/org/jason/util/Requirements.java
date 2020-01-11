package org.jason.util;

/**
 * This class is to enhance java.util.Objects
 * 
 * @author Jason Zhang
 *
 */
public final class Requirements {

  private Requirements() {
    throw new AssertionError("No " + Requirements.class + " instances for you!");
  }
  
  public static void requireTrue(boolean parameter) {
    requireTrue(parameter, "");
  }
  
  public static void requireTrue(boolean parameter, String message) {
    if (!parameter){
      throw new IllegalArgumentException(message);
    }
  }
  
  public static <T> void requireEqual(T left, T right) {
    if (left == null || right == null) throw new NullPointerException();
    
    if (!left.equals(right)) throw new ParameterNotEqualException();

  }
  
  @SuppressWarnings("serial")
  private static class ParameterNotEqualException extends RuntimeException{}
  
}
