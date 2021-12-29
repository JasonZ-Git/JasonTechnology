package crack_code_interview_test;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Triple;
import org.jason.code_practice.Q1_9_String_Rotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Q1_9_String_Rotation_Test {

  @ParameterizedTest
  @MethodSource("params")
  public void test(Triple<String, String, Boolean> params) {
    boolean actual = Q1_9_String_Rotation.rotationEqual(params.getLeft(), params.getMiddle());

    Assertions.assertEquals(params.getRight(), actual);
  }

  private static Stream<Triple<String, String, Boolean>> params() {
    return Stream.of(Triple.of("erbottlewat", "waterbottle", true), Triple.of("erbottlewat", "taterbottle", false));
  }
}
