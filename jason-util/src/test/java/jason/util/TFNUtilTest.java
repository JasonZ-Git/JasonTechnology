package jason.util;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.jason.util.TFNUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TFNUtilTest {

  @ParameterizedTest
  @MethodSource("generator")
  public void testTFN(Pair<String, Boolean> pairParam) {
    Assertions.assertEquals(pairParam.getValue(), TFNUtil.isValidTFN(pairParam.getKey()));
  }

  private static Stream<Pair<String, Boolean>> generator() {

    return Stream.of(Pair.of("123456782", Boolean.TRUE), Pair.of("123456783", Boolean.FALSE));
  }
}
