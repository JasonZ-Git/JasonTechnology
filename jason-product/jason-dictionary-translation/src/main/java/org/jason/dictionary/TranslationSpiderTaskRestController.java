package org.jason.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.ToRefactor;
import org.jason.dictionary.helper.DictionaryTranslationHelper;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.WordTranslation;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class TranslationSpiderTaskRestController {

    private static Map<String, String> wordTranslations = new HashMap<>();

    private static Logger logger = LogManager.getLogger(TranslationSpiderTaskRestController.class);

    // Init Once Only
    static {
        wordTranslations = DictionaryTranslationHelper.readExistingDictionary();
    }

    // It should be a PutMapping, will change later
    @ToRefactor
    @GetMapping("startTranslationSpider")
    public String startSpiderTask() {
        List<String> newWords = DictionaryTranslationHelper.readNewWords();

        List<WordTranslation> translations = GoogleTranslationHelper.getTranslations(newWords);

        String newWordTranslationFileResult = translations.stream().map(item -> item.toString()).distinct().collect(Collectors.joining(System.lineSeparator()));

        JasonDictionaryAPI.writeToTempTranslationFile(newWordTranslationFileResult);

        return newWordTranslationFileResult;
    }
}
