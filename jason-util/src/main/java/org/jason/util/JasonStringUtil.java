package org.jason.util;

import org.jason.util.finalclass.StringPair;

import javax.annotation.Nonnull;

public final class JasonStringUtil {

    private static final String QUOTED_FORMAT = "\"%s\"";
    private static final String SINGLE_QUOTED_FORMAT = "'%s'";
    private static final String BRACKET_FORMAT = "(%s)";
    private static final String SQUARE_BRACKET_FORMAT = "[%s]";

    public static String quoted(@Nonnull String source) {
        return String.format(QUOTED_FORMAT, source);
    }

    public static String singleQuoted(@Nonnull String source) {
        return String.format(SINGLE_QUOTED_FORMAT, source);
    }


    public static String squareBracketed(@Nonnull String source) {
        return String.format(SQUARE_BRACKET_FORMAT, source);
    }

    public static String bracketed(@Nonnull String source) {
        return String.format(BRACKET_FORMAT, source);
    }

    public static StringPair cutTwoBy(@Nonnull String source, @Nonnull String cut){
        if (notContains(source, cut)) return null;

        String[] splits = source.split(cut, 2);

        if (splits.length == 1)  return StringPair.of(splits[0], "");

        return StringPair.of(splits[0], splits[1]);
    }

    public static boolean notContains(@Nonnull String source, @Nonnull String sub) {
        return !source.contains(sub);
    }
}
