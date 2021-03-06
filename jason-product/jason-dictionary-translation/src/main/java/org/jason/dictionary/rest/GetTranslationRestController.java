package org.jason.dictionary.rest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;

import java.util.*;

@RestController
@RequestMapping("/get")
public class GetTranslationRestController {

    private static Logger logger = LogManager.getLogger(GetTranslationRestController.class);

    @GetMapping(value = "/{word}")
    @ResponseStatus(HttpStatus.OK)
    public WordTranslation getTranslation(@PathVariable @Nonnull String word) {
        Objects.requireNonNull(word);
        if (DictionaryCache.contains(word)) {
            return WordTranslation.build(word, DictionaryCache.getTranslation(word));
        }

        // If not find in existing list, then fetch translation from Google
        WordTranslation wordTranslation = GoogleTranslationHelper.getTranslation(word);

        JasonDictionaryAPI.writeToNewDictionary(wordTranslation.toString());

        DictionaryCache.put(word, wordTranslation.getTranslation());

        return wordTranslation;
    }

    @GetMapping(value = "/newWords")
    @ResponseStatus(HttpStatus.OK)
    public String getNewTranslations() {

        List<String> translations = JasonDictionaryAPI.readNewDictionary();

        return String.join("<br>", translations);
    }

    @GetMapping(value = "/countAll")
    public Integer getVocabularyCount() {
        return DictionaryCache.size();
    }

    private static List<String> readExternalDictionary() {
        return JasonDictionaryAPI.readFinalDictionary();
    }
}
