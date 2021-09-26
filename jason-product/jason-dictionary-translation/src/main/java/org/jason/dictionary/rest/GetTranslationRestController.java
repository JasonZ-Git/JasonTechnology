package org.jason.dictionary.rest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.dictionary.service.DictionaryService;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class GetTranslationRestController {

  @Autowired
  private DictionaryService dictionaryService;

  private static Logger logger = LogManager.getLogger(GetTranslationRestController.class);

  @GetMapping(value = "/{word}")
  @ResponseStatus(HttpStatus.OK)
  public WordTranslation getTranslation(@PathVariable @Nonnull String word) {
    Objects.requireNonNull(word);
    List<String> translations = dictionaryService.getTranslations(word);
    if (!CollectionUtils.isEmpty(translations)) {
      String translation = translations.stream().collect(Collectors.joining(","));
      return WordTranslation.build(word, translation);
    }

    return WordTranslation.build(word, "Not found");



    /*
     * // If not find in existing list, then fetch translation from Google WordTranslation wordTranslation = GoogleTranslationHelper.getTranslation(word);
     * 
     * JasonDictionaryAPI.writeToNewDictionary(wordTranslation.toString());
     * 
     * DictionaryCache.put(word, wordTranslation.getTranslation());
     * 
     * return wordTranslation;
     */
  }

  // @GetMapping(value = "/newWords")
  @ResponseStatus(HttpStatus.OK)
  public String getNewTranslations() {

    List<String> translations = JasonDictionaryAPI.readNewDictionary();

    return String.join("<br>", translations);
  }

  // @GetMapping(value = "/countAll")
  public Integer getVocabularyCount() {
    return DictionaryCache.size();
  }

  private static List<String> readExternalDictionary() {
    return JasonDictionaryAPI.readFinalDictionary();
  }
}
