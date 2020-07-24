package org.jason.dictionary.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

public class DictionaryCache {

    private static final Logger logger = LogManager.getLogger();

    private static final DictionaryHolder DICTIONARY = DictionaryHolder.getDictionary();

    public static boolean contains(String word) {
        return DICTIONARY.containsKey(word);
    }

    public static boolean contains(String word, String translation) {
        return DICTIONARY.entrySet().contains(new AbstractMap.SimpleEntry<String, String>(word, translation));
    }

    public static String getTranslation(String word) {
        return DICTIONARY.get(word);
    }

    public static void put(String word, String translation) {
        DICTIONARY.put(word, translation);
    }

    public static int size() {
        return DICTIONARY.size();
    }

    private static class DictionaryHolder extends HashMap<String, String>{
        private static DictionaryHolder wordTranslations;

        public static DictionaryHolder getDictionary(){
            if (wordTranslations == null) {
                wordTranslations = new DictionaryHolder();
            }

            return wordTranslations;
        }

        private DictionaryHolder() {
            super();

            List<String> lines = JasonDictionaryAPI.readExistingDictionary();

            for (String current : lines) {
                WordTranslation temp = WordTranslation.buildFromTranslationLine(current);

                if (temp == null) continue;

                put(temp.getWord(), temp.getTranslation());
            }
        }
    }
}
