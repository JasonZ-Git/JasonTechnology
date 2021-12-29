package jason.util;

import java.util.List;
import org.jason.util.algorithm.FabonacciUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FabonacciUtilTester {
  @Test
  public void testFabonacci() {
    Assertions.assertEquals(1, FabonacciUtil.getFabonacciNumber(1));
    Assertions.assertEquals(1, FabonacciUtil.getFabonacciNumber(2));
    Assertions.assertEquals(2, FabonacciUtil.getFabonacciNumber(3));
    Assertions.assertEquals(3, FabonacciUtil.getFabonacciNumber(4));
    Assertions.assertEquals(5, FabonacciUtil.getFabonacciNumber(5));
    Assertions.assertEquals(8, FabonacciUtil.getFabonacciNumber(6));
    Assertions.assertEquals(13, FabonacciUtil.getFabonacciNumber(7));
    Assertions.assertEquals(21, FabonacciUtil.getFabonacciNumber(8));
  }

  @Test
  public void testFabonacciSeries() {
    List<Integer> series = FabonacciUtil.getFabonacciSeries(5);
    Assertions.assertEquals(5, series.size());
    Assertions.assertEquals(1, series.get(0).intValue());
  }
}
