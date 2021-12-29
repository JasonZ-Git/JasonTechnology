package jason.util;

import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TempTest {

  @Test
  @Disabled("No Assertion yet")
  public void tempTest() {
    ForkJoinPool commonPool = ForkJoinPool.commonPool();

    System.out.println(commonPool);
  }
}
