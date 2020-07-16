package org.jason.dictionary.helper;

import java.net.URL;

public class TranslationResult {
    private String word;
    private String translation;
    private URL pronounceURL;

    private TranslationResult(String word, String translation, URL prUrl) {
        this.word = word;
        this.translation = translation;
        this.pronounceURL = prUrl;
    }

    public static TranslationResult build(String word, String translation, URL prUrl) {
        return new TranslationResult(word, translation, prUrl);
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public URL getPronounceURL() {
        return pronounceURL;
    }

    public String wordWithTranslation() {
        return String.format("%s=%s", word, translation);
    }
}
