package org.jason.dictionary;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jason.dictionary.helper.DictionaryTranslationHelper;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;

import java.util.*;

@RestController
public class TranslationRestController {

    private static Map<String, String> wordTranslations = new HashMap<>();

    private static Logger logger = LogManager.getLogger(TranslationRestController.class);

    // Init Once Only
    static {
        wordTranslations = DictionaryTranslationHelper.readExistingDictionary();
    }

    @GetMapping(value = "getTranslation")
    public WordTranslation getTranslation(@RequestParam @Nonnull String word) {
        Objects.requireNonNull(word);

        if (wordTranslations.containsKey(word)) {
            return WordTranslation.build(word, wordTranslations.get(word));
        }

        // If not find in existing list, then fetch translation from Google
        return GoogleTranslationHelper.getTranslation(word);
    }

    @GetMapping(value = "count")
    public Integer getVocabularyCount() {
        return wordTranslations.size();
    }

    private static List<String> readExternalDictionary() {
        return JasonDictionaryAPI.readExistingDictionary();
    }
}
