package org.jason.util;

/**
 * This class is to enhance java.util.Objects
 *
 * @author Jason Zhang
 */
public final class Parameters {

  private Parameters() {
    throw new AssertionError("No " + Parameters.class + " instances for you!");
  }

  public static <T extends Throwable> void requireNonnull(boolean parameter, T throwClass) {
    if (!parameter) {

    }
  }

  public static void requireTrue(boolean parameter) {
    requireTrue(parameter, "");
  }

  public static void requireTrue(boolean parameter, String message) {
    if (!parameter) {
      throw new IllegalArgumentException(message);
    }
  }

}
