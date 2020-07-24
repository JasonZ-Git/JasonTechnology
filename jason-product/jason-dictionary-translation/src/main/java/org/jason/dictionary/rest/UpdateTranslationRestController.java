package org.jason.dictionary.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import java.util.Objects;

@RestController
public class UpdateTranslationRestController {

    private static Logger logger = LogManager.getLogger(UpdateTranslationRestController.class);

    @PutMapping(value = "newTranslation")
    public String insertTranslation(@RequestParam @Nonnull String word, @RequestParam @Nonnull String translation) {
        Objects.requireNonNull(word);

        if (DictionaryCache.contains(word)) {

            if (DictionaryCache.contains(word, translation)) {
                return "Existing word and translation: " + String.join("=", word, translation);
            }

            String existingTrans = DictionaryCache.getTranslation(word);

            String existingTranslationItem = String.join("=", word, DictionaryCache.getTranslation(word));
            String newTranslationItem = String.join("=", word, translation);
            JasonDictionaryAPI.replaceTranslationOfNewDictionary(existingTranslationItem, newTranslationItem);

            DictionaryCache.put(word, translation);

            String msgFormat = "Update translation for word '%s' from '%s' to '%s'";

            return String.format(msgFormat, word, existingTrans, translation);
        }

        JasonDictionaryAPI.writeToNewDictionary(String.join("=", word, translation));

        DictionaryCache.put(word, translation);

        logger.info("Write new dictionary added: " + String.join("=", word, translation));

        return String.format("New translation for word '%s' is added %s", word, translation);
    }
}
