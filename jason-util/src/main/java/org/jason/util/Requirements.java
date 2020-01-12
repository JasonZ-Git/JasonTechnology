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
  
  public static void requireFalse(boolean parameter) {
    requireTrue(!parameter);
  }
  
  public static void requireFalse(boolean parameter, String message) {
    requireTrue(!parameter, message);
  }
  
  public static <T> void requireEqual(T left, T right) {
	  requireEqual(left, right, null);
  }
  
  public static <T> void requireEqual(T left, T right, String message) {
    if (left == null || right == null) throw new NullPointerException(message);
	    
	if (!left.equals(right)) throw new RuntimeException(message);
  }
  
  public static <T> void requireNotEqual(T left, T right) {
	  requireNotEqual(left, right, null);
  }
  
  public static <T> void requireNotEqual(T left, T right, String message) {
    if (left == null || right == null) throw new NullPointerException(message);
	    
	if (left.equals(right)) throw new RuntimeException(message);
  }
}
