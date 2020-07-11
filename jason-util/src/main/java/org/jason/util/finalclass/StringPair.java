package org.jason.util.finalclass;

public final class StringPair extends Pair<String, String> {

    private StringPair(String left, String right) {
        super(left, right);
    }

    public static StringPair of(String left, String right) {
        return new StringPair(left, right);
    }
}
