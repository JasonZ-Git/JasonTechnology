package org.jason.dictionary.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryTranslationHelper {

    private static final Logger logger = LogManager.getLogger();

    public static Map<String, String> readExistingDictionary() {
        Map<String, String> wordTranslations = new HashMap<>();
        List<String> lines = JasonDictionaryAPI.readExistingDictionary();

        for (String current : lines) {
            WordTranslation temp = WordTranslation.buildFromTranslationLine(current);

            if (temp == null) continue;

            wordTranslations.put(temp.getWord(), temp.getTranslation());
        }

        return wordTranslations;
    }

    /** new words that is not existing in existing dictionary file */
    public static List<String> readNewWords() {
        List<String> newWords = JasonDictionaryAPI.readNewWords();
        List<String> realNewWords = JasonDictionaryAPI.filterRealWords(newWords);

        Map<String, String> existingDict = readExistingDictionary();

        realNewWords.removeIf(existingDict::containsKey);

        return realNewWords;
    }
}
