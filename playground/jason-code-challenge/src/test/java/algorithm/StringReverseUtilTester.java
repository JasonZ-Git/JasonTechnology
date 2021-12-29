package algorithm;

import org.jason.algorithm.StringReverseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringReverseUtilTester {

  @Test
  public void tester() {
    Assertions.assertEquals(StringReverseUtil.reverse("hello"), "olleh");
  }
}
