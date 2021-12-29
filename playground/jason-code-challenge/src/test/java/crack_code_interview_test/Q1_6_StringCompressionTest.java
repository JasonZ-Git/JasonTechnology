package crack_code_interview_test;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.jason.code_practice.Q1_6_String_Compression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Q1_6_StringCompressionTest {

  @ParameterizedTest
  @MethodSource("generate")
  public void testCompress(Pair<String, String> param) {
    String actual = Q1_6_String_Compression.compress(param.getKey());

    Assertions.assertEquals(param.getValue(), actual);
  }

  private static Stream<Pair<String, String>> generate() {
    return Stream.of(Pair.of("aabccccaaa", "a2b1c4a3"), Pair.of("abcVcDBa", "abcVcDBa"));
  }
}
