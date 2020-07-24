package org.jason.dictionary.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.util.Objects;

@RestController
public class DeleteTranslationRestController {

    private static Logger logger = LogManager.getLogger();

    @DeleteMapping(value = "deleteTranslation")
    public String deleteTranslation(@RequestParam @Nonnull String word) {
        Objects.requireNonNull(word);

        String translation = DictionaryCache.getTranslation(word);

        JasonDictionaryAPI.deleteTranslationOfNewDictionary(String.format("%s=%s", word, translation));

        DictionaryCache.remove(word);

        return String.format("Word '%s'is deleted", word);
    }
}
