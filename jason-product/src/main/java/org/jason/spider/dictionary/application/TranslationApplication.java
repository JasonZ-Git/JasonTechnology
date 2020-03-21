package org.jason.spider.dictionary.application;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.spider.PageSpider;
import org.jason.spider.dictionary.TranslationResult;
import org.jason.spider.dictionary.WordTranslationSpider;
import org.jason.util.JasonFileUtil;
import org.jason.util.translation.JasonDictionaryUtil;

@Application(name = "Translation Application")
public class TranslationApplication {

  private static final String SourceFile = "dictionary/source.txt";

  private static final String Dictionary_File = "dictionary/dictionary.properties";

  private static final String BASE_DICIONARY_DIR = "/home/jason/projects/jason-technology/jason-product/src/main/resources/dictionary/";
  
  private static final String Target_Dictionary_File = BASE_DICIONARY_DIR + "dictionary.properties";

  private static final String Temporary_Dictionary_File = BASE_DICIONARY_DIR + "temporary_dictionary.properties";
  
  private static final String PRONOUNCE_DIR = BASE_DICIONARY_DIR + "pronounce/";
  
  private static final String MP3_FORMAT = ".mp3";

  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) throws IOException, URISyntaxException {
    runTranslationApp();
    // removeUnvalidTranslation();
  }

  private static void runTranslationApp() throws IOException, URISyntaxException {

    StopWatch watch = StopWatch.createStarted();

    List<String> sourceWords = JasonFileUtil.readFileFromClasspathIntoWord(SourceFile);

    List<String> sourceWordsToTranslate = JasonDictionaryUtil.filterRealWords(sourceWords);

    List<String> existingWordsLines = JasonFileUtil.readFileFromClasspathIntoWord(Dictionary_File);

    List<String> existingWords = JasonDictionaryUtil.getWords(existingWordsLines);

    sourceWordsToTranslate.removeIf(existingWords::contains);

    List<String> limitedWords = sourceWordsToTranslate.stream().limit(1).collect(Collectors.toList());

    PageSpider<TranslationResult> spider = new WordTranslationSpider(limitedWords);

    List<TranslationResult> result = spider.crawl();

    String newWordTranslation = result.stream().map(item -> item.wordWithTranslation()).distinct().collect(Collectors.joining(System.lineSeparator()));
    JasonFileUtil.appendToFile(Target_Dictionary_File, newWordTranslation);

    // Save Pronounce File
    for (TranslationResult current : result) {
      FileUtils.copyURLToFile(current.getPronounceURL(), new File(PRONOUNCE_DIR, current.getWord()+ MP3_FORMAT));
    }

    watch.stop();

    logger.info("Existing Dictionary Words number is: {} ", existingWords.size());
    logger.info("Total Words to translate {} - {} ", limitedWords.size(), limitedWords);
    logger.info("Total running time is:{} seconds  ", watch.getTime(TimeUnit.SECONDS));
  }


  private static void removeUnvalidTranslation() throws IOException {
    List<String> sourceWords = JasonFileUtil.readFile(Target_Dictionary_File);

    logger.info(sourceWords.size());
    logger.info(sourceWords);
    String targetContent = sourceWords.stream().peek(item -> {
      if (item.contains("..."))
        System.out.println(item);
    }).filter(item -> !item.contains("...")).collect(Collectors.joining(System.lineSeparator()));

    JasonFileUtil.writeFile(Temporary_Dictionary_File, targetContent);
    logger.info("Finished");
  }

}
