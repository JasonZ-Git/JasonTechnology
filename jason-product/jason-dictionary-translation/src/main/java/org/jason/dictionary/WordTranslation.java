package org.jason.dictionary;

import javax.annotation.Nonnull;

public class WordTranslation {
    final private String word;
    final private String translation;

    private WordTranslation(@Nonnull String word, @Nonnull String translation){
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

    public static WordTranslation buildFromTranslationLine(@Nonnull String translationLine){
       if (! translationLine.contains("=")){
           throw new IllegalArgumentException("It should contains a '='");
       }

       String[] wordTrans = translationLine.split("=");

       return build(wordTrans[0], wordTrans[1]);
    }
}
