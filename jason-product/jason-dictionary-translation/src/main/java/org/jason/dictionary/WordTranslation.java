package org.jason.dictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class WordTranslation {
    final private String word;
    final private String translation;
    private static Logger logger = LogManager.getLogger(WordTranslation.class);

    private WordTranslation(@Nonnull String word, @Nonnull String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getWord() {
        return word;
    }


    public String getTranslation() {
        return translation;
    }

    public static WordTranslation build(@Nonnull String word, @Nonnull String translation) {
        return new WordTranslation(word, translation);
    }

    /**
     * @param translationLine It should be filtered by regex %s=%s
     */
    public static WordTranslation buildFromTranslationLine(@Nonnull String translationLine) {
        if (translationLine == null && !translationLine.contains("=") || translationLine.split("=").length != 2) {

            throw new IllegalArgumentException(translationLine + " doesn't contains a '='");
        }

        String[] wordTrans = translationLine.split("=");

        return build(wordTrans[0], wordTrans[1]);
    }
}
