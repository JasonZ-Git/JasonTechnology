package org.jason.dictionary.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;

import java.util.List;

public class PronunciationCache {
    private static final Logger logger = LogManager.getLogger();

    private static List<String> pronunciationCache;

    static {
        pronunciationCache = JasonDictionaryAPI.readFinalPronunciations();
        pronunciationCache.addAll(JasonDictionaryAPI.readNewPronunciations());
    }

    public static boolean exists(String word) {
        return pronunciationCache.contains(word);
    }

}
