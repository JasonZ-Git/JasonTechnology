package org.jason.learn.google.test;

import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.jason.learn.google.StringDecompress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class StringDecompressTest {

  @ParameterizedTest
  @MethodSource("params")
  public void test(Pair<String, String> param) {
    String actual = StringDecompress.decompress(param.getKey());

    Assertions.assertEquals(param.getValue(), actual);
  }

  private static Stream<Pair<String, String>> params() {
    return Stream.of(Pair.of("3[abc]4[ab]c", "abcabcabcababababc"), Pair.of("2[3[a]b]", "aaabaaab"), Pair.of("10[a]", "aaaaaaaaaa"));
  }
}
