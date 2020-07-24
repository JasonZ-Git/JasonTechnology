package org.jason.util.dictionary;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.ToRefactor;
import org.jason.util.JasonFileUtil;
import org.jason.util.finalclass.StringPair;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This Utility works with a line of String which contains a translation Item.
 * So if the structure changes in the future, this class could keep unchanged.
 *
 */
public final class JasonDictionaryAPI {
    private final static String formatter = "{\"%s\": \"%s\",\"%s\": \"%s\",\"%s\": %d}";

    private final static String DICTIONARY_DIR_UBUNTU = "/home/jason/Desktop/Jason-Files/dictionary/";
    private final static String DICTIONARY_DICTIONARY_MAC = "TO BE DECIDED";
    private final static String DICTIONARY_FILENAME = "final-dictionary.properties";
    private final static String NEW_DICTIONARY_FILENAME = "new-dictionary.properties";
    private final static String WORD_TO_TRANSLATE_FILENAME = "word_to_translate.txt";
    private final static String TEMP_DICTIONARY_FILENAME = "temporary_dictionary.properties";
    private final static String WORD_TRANSLATION = "%s=%s";

    private final static Logger logger = LogManager.getLogger();

    public static List<String> readExistingDictionary() {

        List<String> result = new ArrayList<>();

        String dictionaryFile = getDictionaryFile(DICTIONARY_FILENAME);
        try {
            result = Files.readAllLines(Paths.get(dictionaryFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static void writeToNewDictionary(String newline) {
        String dictionaryFile = getDictionaryFile(NEW_DICTIONARY_FILENAME);
        try {
            String line = newline + System.lineSeparator();
            Files.write(Paths.get(dictionaryFile), Arrays.asList(line), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void replaceTranslation(String oldLine, String newLine){
        List<String> allLines = new ArrayList<>();

        String dictionaryFile = getDictionaryFile(DICTIONARY_FILENAME);
        try {
            allLines = Files.readAllLines(Paths.get(dictionaryFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        allLines.removeIf(item -> item.equals(oldLine));

        allLines.add(newLine);

        try {
            Files.write(Paths.get(dictionaryFile), allLines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Read words from a file
    public static List<String> readNewWords() {
        List<String> result = new ArrayList<>();

        String newSourceWordFile = getDictionaryFile(WORD_TO_TRANSLATE_FILENAME);

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

        String file = getDictionaryFile(TEMP_DICTIONARY_FILENAME);
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

    private static String getDictionaryFile(String filename) {
        String osName = System.getProperty("os.name");
        String dictionaryFile = null;
        if (osName.equalsIgnoreCase("Linux")) {
            dictionaryFile = DICTIONARY_DIR_UBUNTU + filename;
        } else if (osName.equalsIgnoreCase("MacOS")) {
            dictionaryFile = DICTIONARY_DICTIONARY_MAC + filename;
        }

        return dictionaryFile;
    }

}
