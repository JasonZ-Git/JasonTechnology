package jason.util;

import java.util.List;

import org.jason.algorithm.FabonacciUtil;
import org.junit.Test;

import junit.framework.Assert;

public class FabonacciUtilTester {
  @Test
  public void testFabonacci() {
    Assert.assertEquals(1, FabonacciUtil.getFabonacciNumber(1));
    Assert.assertEquals(1, FabonacciUtil.getFabonacciNumber(2));
    Assert.assertEquals(2, FabonacciUtil.getFabonacciNumber(3));
    Assert.assertEquals(3, FabonacciUtil.getFabonacciNumber(4));
    Assert.assertEquals(5, FabonacciUtil.getFabonacciNumber(5));
    Assert.assertEquals(8, FabonacciUtil.getFabonacciNumber(6));
    Assert.assertEquals(13, FabonacciUtil.getFabonacciNumber(7));
    Assert.assertEquals(21, FabonacciUtil.getFabonacciNumber(8));
  }

  @Test
  public void testFabonacciSeries() {
    List<Integer> series = FabonacciUtil.getFabonacciSeries(5);
    Assert.assertEquals(5, series.size());
    Assert.assertEquals(1, series.get(0).intValue());
  }
}
