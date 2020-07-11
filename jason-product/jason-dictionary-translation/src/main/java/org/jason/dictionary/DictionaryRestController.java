package org.jason.dictionary;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
public class DictionaryRestController {

    public static final String DICTIONARY_FILE = "dictionary.properties";

    private Map<String, String> wordTranslations = new HashMap<>();

    private static Logger logger = LogManager.getLogger(DictionaryRestController.class);

    @RequestMapping(value = "getTranslation", method = RequestMethod.GET)
    public WordTranslation getTranslation(@RequestParam @Nonnull String word) {
        Objects.requireNonNull(word);

        if (wordTranslations.isEmpty()) {
            init();
        }

        return WordTranslation.build(word, wordTranslations.get(word));
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Integer getVocabularyCount() {
        if (wordTranslations.isEmpty()) {
            init();
        }

        return wordTranslations.size();
    }

    private void init() {
        if (wordTranslations.isEmpty()) {

            List<String> lines = null;
            try {
                Path path = Paths.get(ClassLoader.getSystemResource(DICTIONARY_FILE).toURI());
                lines = Files.readAllLines(path);
            } catch (Exception ex) {
                logger.error(ex);
            }

            for (String current : lines) {
                WordTranslation temp = WordTranslation.buildFromTranslationLine(current);

                if (temp == null) continue;

                wordTranslations.put(temp.getWord(), temp.getTranslation());
            }
        }
    }
}
