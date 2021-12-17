package org.jason.dictionary.rest;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryResource {
  private static Logger logger = LogManager.getLogger(DictionaryResource.class);

  @GetMapping(value = "/dictionary/{word}")
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

  @GetMapping(value = "/dictionary/newWords")
  public String getNewTranslations() {

    List<String> translations = JasonDictionaryAPI.readNewDictionary();

    return String.join("<br>", translations);
  }

  @GetMapping(value = "/dictionary/countAll")
  public Integer getVocabularyCount() {
    return DictionaryCache.size();
  }

  @DeleteMapping(value = "/dictionary/{word}")
  public String deleteTranslation(@PathVariable @Nonnull String word) {
    Objects.requireNonNull(word);

    String translation = DictionaryCache.getTranslation(word);

    JasonDictionaryAPI.deleteTranslationOfNewDictionary(String.format("%s=%s", word, translation));

    DictionaryCache.remove(word);

    return String.format("Word '%s'is deleted", word);
  }


  @PutMapping(value = "/dictionary/{word}")
  public String insertTranslation(@Nonnull @PathVariable String word, @RequestParam @Nonnull String translation) {
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
