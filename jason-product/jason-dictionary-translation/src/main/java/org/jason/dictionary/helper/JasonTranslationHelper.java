package org.jason.dictionary.helper;

import org.jason.util.dictionary.JasonDictionaryAPI;

import java.util.List;
import java.util.Map;

public class JasonTranslationHelper {
    /** new words that is not existing in existing dictionary file */
    public static List<String> readNewWords() {
        List<String> newWords = JasonDictionaryAPI.readNewWords();

        List<String> realNewWords = JasonDictionaryAPI.filterRealWords(newWords);

        realNewWords.removeIf(DictionaryCache::contains);

        return realNewWords;
    }
}
