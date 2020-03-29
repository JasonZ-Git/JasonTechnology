package org.jason.spider.dictionary;

import org.jason.util.finalclass.Pair;

public class StringPair extends Pair<String, String> {
  private StringPair(String left, String right) {
    super(left, right);
  }
  
  public static StringPair of(String left, String right) {
    return new StringPair(left, right);
  }
}
