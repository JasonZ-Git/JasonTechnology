package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.util.JasonFileUtil;

import static org.jason.spider.dictionary.DictionaryConstants.*;

@Application(name = "Translation Application")
public class DictionaryCleaningApplication {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException, URISyntaxException {
        cleanUpDictionary();
        cleanUpPronounceUrl();
    }

    private static void cleanUpPronounceUrl() throws IOException {
        List<String> pronounceList = JasonFileUtil.readFile(PRONOUNCE_FILE);

        String pronounceResult = pronounceList.parallelStream().filter(DictionaryCleaningApplication::keepProunouce).distinct().collect(Collectors.joining(System.lineSeparator()));

        JasonFileUtil.writeFile(TEMPORARY_PRONOUNCE_FILE, pronounceResult);

        int pronounceResultSize = pronounceResult.split(System.lineSeparator()).length;

        logger.info("Pronounce Cleaning Finished {} items cleaned", pronounceList.size() - pronounceResultSize);
    }

    private static void cleanUpDictionary() throws IOException {
        List<String> sourceWords = JasonFileUtil.readFile(DICTIONARY_FILE);

        String targetContent = sourceWords.stream().filter(DictionaryCleaningApplication::keepTranslation).distinct().collect(Collectors.joining(System.lineSeparator()));

        JasonFileUtil.writeFile(TEMPORARY_DICTIONARY_FILE, targetContent);

        int resultDictionarySize = targetContent.split(System.lineSeparator()).length;

        logger.info("Dictionary Cleaning Finished, {} items cleaned", sourceWords.size() - resultDictionarySize);
    }

    private static boolean keepProunouce(String pronounceItem) {
        if (pronounceItem.split("=").length == 1)
            return false;

        String pronounceURL = pronounceItem.split("=")[1];

        if (StringUtils.equalsAnyIgnoreCase(pronounceURL, "", "null"))
            return false;

        return true;
    }

    private static boolean keepTranslation(String sourceItem) {

        if (sourceItem.split("=").length == 1)
            return false;

        String translation = sourceItem.split("=")[1];

        if (StringUtils.equalsAny(translation, "", "null", "..."))
            return false;

        return true;

    }
}
