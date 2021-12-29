package crack_code_interview_test;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Triple;
import org.jason.code_practice.Q1_5_One_Way;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Q1_5_One_Way_Test {

  @ParameterizedTest
  @MethodSource("sourceParams")
  public void test(Triple<String, String, Boolean> param) {
    boolean actual = Q1_5_One_Way.isOneWay(param.getLeft(), param.getMiddle());

    Assertions.assertEquals(actual, param.getRight());
  }

  private static Stream<Triple<String, String, Boolean>> sourceParams() {
    return Stream.of(Triple.of("pale", "ple", true), Triple.of("pales", "pale", true), Triple.of("pale", "bale", true), Triple.of("pale", "bake", false));
  }
}
