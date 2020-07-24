package org.jason.dictionary.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.ToRefactor;
import org.jason.dictionary.helper.DictionaryCache;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.JasonTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TranslationSpiderTaskRestController {

    private static Logger logger = LogManager.getLogger();

    // It should be a PutMapping, will change later
    @ToRefactor
    @GetMapping("startTranslationSpider")
    public List<String> startSpiderTask() {
        List<String> newWords = JasonTranslationHelper.readNewWords();

        List<WordTranslation> translations = GoogleTranslationHelper.getTranslations(newWords);

        List<String> newLines = translations.stream().map(item -> item.toString()).distinct().collect(Collectors.toList());

        JasonDictionaryAPI.writeToNewDictionary(newLines);

        translations.stream().forEach(item -> DictionaryCache.put(item.getWord(), item.getTranslation()));

        return newLines;
    }
}
