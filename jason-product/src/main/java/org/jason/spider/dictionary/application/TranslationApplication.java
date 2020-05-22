package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.spider.PageSpider;
import org.jason.spider.dictionary.TranslationResult;
import org.jason.spider.dictionary.WordTranslationSpider;
import org.jason.util.JasonFileUtil;
import org.jason.util.translation.JasonDictionaryUtil;
import static org.jason.spider.dictionary.DictionaryConstants.*;

@Application(name = "Translation Application")
public class TranslationApplication {

  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) throws IOException, URISyntaxException {
    runTranslationApp();
  }

  private static void runTranslationApp() throws IOException, URISyntaxException {

    StopWatch watch = StopWatch.createStarted();

    List<String> sourceWords = JasonFileUtil.readFileIntoWords(SOURCE_FILE);

    List<String> sourceWordsToTranslate = JasonDictionaryUtil.filterRealWords(sourceWords);

    List<String> existingWordsLines = JasonFileUtil.readFileIntoWords(DICTIONARY_FILE);

    List<String> existingWords = JasonDictionaryUtil.getWords(existingWordsLines);

    sourceWordsToTranslate.removeIf(existingWords::contains);

    System.out.printf("Total words to translate is %d", sourceWordsToTranslate.size());

    List<String> limitedWords = sourceWordsToTranslate.stream().limit(40000).collect(Collectors.toList());

    PageSpider<TranslationResult> spider = new WordTranslationSpider(limitedWords);

    List<TranslationResult> result = spider.crawl();

    String newWordTranslation = result.stream().map(item -> item.wordWithTranslation()).distinct().collect(Collectors.joining(System.lineSeparator()));
    JasonFileUtil.appendToFile(DICTIONARY_FILE, newWordTranslation);

    List<String> existingPronounceUrls = JasonFileUtil.readFile(PRONOUNCE_FILE);

    String newWordProunouceURL =
        result.stream().map(item -> item.getWord() + "=" + item.getPronounceURL()).filter(item -> !existingPronounceUrls.contains(item)).distinct().collect(Collectors.joining(System.lineSeparator()));
    JasonFileUtil.appendToFile(PRONOUNCE_FILE, newWordProunouceURL);

    watch.stop();

    logger.info("Existing Dictionary Words number is: {} ", existingWords.size());
    logger.info("Total Words to translate {} - {} ", limitedWords.size(), limitedWords);
    logger.info("Total running time is:{} seconds  ", watch.getTime(TimeUnit.SECONDS));
  }

}
