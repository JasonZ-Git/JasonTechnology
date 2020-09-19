package org.jason.dictionary.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class PronunciationCache {
    private static final Logger logger = LogManager.getLogger();

    private static List<String> pronunciationCache;
    private static List<Path> pronunciationCachePath;

    static {
        pronunciationCachePath = JasonDictionaryAPI.readFinalPronunciations();
        pronunciationCachePath.addAll(JasonDictionaryAPI.readNewPronunciation());

        pronunciationCache = pronunciationCachePath.stream().map(item -> item.getFileName().toString()).collect(Collectors.toList());
    }

    public static boolean exists(String word) {
        return pronunciationCache.contains(word + ".mp3");
    }

    public static Path getPath(String word) {
        if (!exists(word)) return null;

        return pronunciationCachePath.stream().filter(item -> item.getFileName().equals(word + ".mp3")).findFirst().get();
    }

    public static int count() {
        return pronunciationCache.size();
    }

}
