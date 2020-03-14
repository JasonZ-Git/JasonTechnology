package org.jason.util.translation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

public final class JasonDictionaryUtil {

  public static List<String> filterRealWords(@Nonnull List<String> words){
    Objects.requireNonNull(words);
    
    return words.parallelStream().filter(StringUtils::isAlpha).filter(StringUtils::isAllLowerCase).filter(item -> item.length() >=2).collect(Collectors.toList());
  }

  public static List<String> getWords(@Nonnull List<String> existingWordsLines) {
    Objects.requireNonNull(existingWordsLines);
    
    return existingWordsLines.parallelStream().map(item -> item.split("=")[0].trim()).collect(Collectors.toList());
  }
  
}
