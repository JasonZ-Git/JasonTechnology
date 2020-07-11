package org.jason.util.translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.jason.util.finalclass.StringPair;

public final class JasonDictionaryUtil {
    final static String formatter = "{\"%s\": \"%s\",\"%s\": \"%s\",\"%s\": %d}";

    public static List<String> filterRealWords(@Nonnull List<String> words) {
        Objects.requireNonNull(words);

        return words.parallelStream().filter(StringUtils::isAlpha).filter(StringUtils::isAllLowerCase).filter(item -> item.length() >= 2).distinct().collect(Collectors.toList());
    }

    public static List<String> getWords(@Nonnull List<String> existingWordsLines) {
        Objects.requireNonNull(existingWordsLines);

        return existingWordsLines.parallelStream().map(item -> item.split("=")[0].trim()).collect(Collectors.toList());
    }

    public static List<String> convertToJson(List<StringPair> wordTranslation) {
        int count = 1;
        List<String> result = new ArrayList<>();
        for (StringPair current : wordTranslation) {
            String stage = String.format(formatter, "word", current.getLeft(), "translation", current.getRight(), "id", count++);

            result.add(stage);
        }

        return result;
    }

}
