package org.jason.dictionary.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.helper.GooglePronunciationHelper;
import org.jason.dictionary.helper.PronunciationCache;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class PronunciationSpiderTaskRestController {

    private static Logger logger = LogManager.getLogger();

    @GetMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public List<String> startSpiderTask() {

        List<String> words = JasonDictionaryAPI.readNewWords();

        words.removeIf(PronunciationCache::exists);

        for (String current : words) {
            GooglePronunciationHelper.downLoadGooglePronunciation(current);
            logger.info("Downloaded word: " + current);
        }

        return words;
    }
}
