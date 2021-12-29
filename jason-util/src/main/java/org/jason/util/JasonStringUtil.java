package org.jason.util;

import org.jason.util.finalclass.StringPair;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class JasonStringUtil {

  private static final String QUOTED_FORMAT = "\"%s\"";
  private static final String SINGLE_QUOTED_FORMAT = "'%s'";
  private static final String BRACKET_FORMAT = "(%s)";
  private static final String SQUARE_BRACKET_FORMAT = "[%s]";

  public static String quoted(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(QUOTED_FORMAT, source);
  }

  public static String singleQuoted(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(SINGLE_QUOTED_FORMAT, source);
  }


  public static String squareBracketed(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(SQUARE_BRACKET_FORMAT, source);
  }

  public static String bracketed(@Nonnull String source) {
    Objects.requireNonNull(source);

    return String.format(BRACKET_FORMAT, source);
  }

  public static StringPair cutTwoBy(@Nonnull String source, @Nonnull String cut) {
    Objects.requireNonNull(source);

    if (notContains(source, cut))
      return null;

    String[] splits = source.split(cut, 2);

    if (splits.length == 1)
      return StringPair.of(splits[0], "");

    return StringPair.of(splits[0], splits[1]);
  }

  public static boolean notContains(@Nonnull String source, @Nonnull String sub) {
    Objects.requireNonNull(source);
    Objects.requireNonNull(sub);

    return !source.contains(sub);
  }
}
