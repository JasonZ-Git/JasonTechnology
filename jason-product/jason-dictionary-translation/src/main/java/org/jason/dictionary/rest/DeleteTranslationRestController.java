package org.jason.dictionary.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.util.Objects;

@RestController
@RequestMapping("/delete")
public class DeleteTranslationRestController {

    private static Logger logger = LogManager.getLogger();

    // @DeleteMapping(value = "/{word}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTranslation(@PathVariable @Nonnull String word) {
        Objects.requireNonNull(word);

        String translation = DictionaryCache.getTranslation(word);

        JasonDictionaryAPI.deleteTranslationOfNewDictionary(String.format("%s=%s", word, translation));

        DictionaryCache.remove(word);

        return String.format("Word '%s'is deleted", word);
    }
}
