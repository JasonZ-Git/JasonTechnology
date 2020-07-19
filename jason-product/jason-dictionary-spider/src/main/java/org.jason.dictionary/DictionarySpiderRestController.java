package org.jason.dictionary;

import org.jason.dictionary.helper.GooglePronunciationHelper;
import org.jason.dictionary.helper.GoogleTranslationHelper;
import org.jason.dictionary.helper.TranslationResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionarySpiderRestController {

    @GetMapping("getGooglePronunciation")
    public byte[] getWordPronunciation(@RequestParam(defaultValue = "hello") String word) {
        TranslationResult translation = GoogleTranslationHelper.getTranslation(word);

        return GooglePronunciationHelper.getPronunciationFromUrl(translation.getWord(), translation.getPronounceURL());
    }
}
