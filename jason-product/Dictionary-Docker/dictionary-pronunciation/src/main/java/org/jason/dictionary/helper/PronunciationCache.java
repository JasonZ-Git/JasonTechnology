package org.jason.dictionary.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;

import java.nio.file.Path;
import java.util.List;

public class PronunciationCache {
    private static final Logger logger = LogManager.getLogger();

    private static List<Path> pronunciationCachePath;

    static {
        pronunciationCachePath = JasonDictionaryAPI.readFinalPronunciations();
        pronunciationCachePath.addAll(JasonDictionaryAPI.readNewPronunciation());
    }

    public static boolean exists(final String word) {
        return pronunciationCachePath.stream().anyMatch(item -> item.endsWith(word + ".mp3"));
    }

    public static Path getPath(final String word) {
        if (!exists(word)) return null;

        return pronunciationCachePath.stream().filter(item -> item.endsWith(word + ".mp3")).findFirst().get();
    }

    public static int count() {
        return pronunciationCachePath.size();
    }

}
