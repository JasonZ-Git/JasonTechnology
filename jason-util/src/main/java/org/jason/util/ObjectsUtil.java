package org.jason.util;

/**
 * This class is to enhance java.util.Objects
 * @author jason zhang
 *
 */
public final class ObjectsUtil {

  private ObjectsUtil() {
    throw new AssertionError("No " + ObjectsUtil.class + " instances for you!");
  }
  
  public static void requireTrue(boolean parameter) {
    requireTrue(parameter, "");
  }
  
  public static void requireTrue(boolean parameter, String message) {
    if (!parameter){
      throw new IllegalArgumentException(message);
    }
  }
  
}
