package org.jason.util.dictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.ToRefactor;
import org.jason.util.JasonFileUtil;
import org.jason.util.finalclass.StringPair;

public final class JasonDictionaryAPI {
    private final static String formatter = "{\"%s\": \"%s\",\"%s\": \"%s\",\"%s\": %d}";

    private final static String DICTIONARY_DICTIONARY_UBUNTU = "/home/jason/Desktop/Jason-Files/dictionary/";
    private final static String DICTIONARY_DICTIONARY_MAC = "TO BE DECIDED";
    private final static String DICTIONARY_FILENAME = "final-dictionary.properties";
    private final static String WORD_TO_TRANSLATE_FILENAME = "word_to_translate.txt";
    private final static String TEMP_DICTIONARY_FILENAME = "temporary_dictionary.properties";

    private final static Logger logger = LogManager.getLogger();

    public static List<String> readExistingDictionary() {

        List<String> result = new ArrayList<>();

        String dictionaryFile = getSystemFile(DICTIONARY_FILENAME);
        try {
            result = Files.readAllLines(Paths.get(dictionaryFile));
        } catch (IOException e) {
            logger.error(e);
            return result;
        }

        return result;
    }

    // Read words from a file
    public static List<String> readNewWords() {
        List<String> result = new ArrayList<>();

        String newSourceWordFile = getSystemFile(WORD_TO_TRANSLATE_FILENAME);

        List<String> existingDict = readExistingDictionary();

        List<String> newSourceWords = new ArrayList<>();
        try {
            newSourceWords = JasonFileUtil.readFileIntoWords(newSourceWordFile);
        } catch (IOException e) {
            logger.error(e);

            throw new RuntimeException(e);
        }

        return newSourceWords;
    }

    public static List<String> filterRealWords(@Nonnull List<String> words) {
        Objects.requireNonNull(words);

        return words.parallelStream().filter(StringUtils::isAlpha).filter(StringUtils::isAllLowerCase).filter(item -> item.length() >= 2).distinct().collect(Collectors.toList());
    }

    public static List<String> getWords(@Nonnull List<String> existingWordsLines) {
        Objects.requireNonNull(existingWordsLines);

        return existingWordsLines.parallelStream().map(item -> item.split("=")[0].trim()).collect(Collectors.toList());
    }

    public static void writeToTempTranslationFile(String translationFileContent) {

        String file = getSystemFile(TEMP_DICTIONARY_FILENAME);
        try {
            JasonFileUtil.writeFile(file, translationFileContent);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @ToRefactor("This is a bad way, should use facility(JSON-B or Jackson) to refactor")
    public static List<String> convertToJson(List<StringPair> wordTranslation) {
        int count = 1;
        List<String> result = new ArrayList<>();
        for (StringPair current : wordTranslation) {
            String stage = String.format(formatter, "word", current.getLeft(), "translation", current.getRight(), "id", count++);

            result.add(stage);
        }

        return result;
    }

    private static String getSystemFile(String filename){
        String osName = System.getProperty("os.name");
        String dictionaryFile = null;
        if (osName.equalsIgnoreCase("Linux")) {
            dictionaryFile = DICTIONARY_DICTIONARY_UBUNTU + filename;
        } else if (osName.equalsIgnoreCase("MacOS")) {
            dictionaryFile =DICTIONARY_DICTIONARY_MAC + filename;
        }

        return dictionaryFile;
    }

}
