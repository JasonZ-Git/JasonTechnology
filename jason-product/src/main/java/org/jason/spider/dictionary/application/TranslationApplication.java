package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.spider.PageSpider;
import org.jason.spider.dictionary.WordTranslationSpider;
import org.jason.util.JasonFileUtil;
import org.jason.util.translation.JasonDictionaryUtil;

@Application(name = "Translation Application")
public class TranslationApplication {

  private static final String SourceFile = "dictionary/source.txt";

  private static final String Dictionary_File = "dictionary/dictionary.properties";

  private static final String Target_Dictionary_File = "/home/jason/projects/jason-technology/jason-product/src/main/resources/dictionary/dictionary.properties";

  private static final String Temporary_Dictionary_File = "/home/jason/projects/jason-technology/jason-product/src/main/resources/dictionary/temporary_dictionary.properties";


  private static final Logger logger = LogManager.getLogger();

  public static void main(String[] args) throws IOException {
    runTranslationApp();
    // removeUnvalidTranslation();
  }

  private static void runTranslationApp() throws IOException {

    StopWatch watch = StopWatch.createStarted();

    List<String> sourceWords = JasonFileUtil.readFileFromClasspathIntoWord(SourceFile);

    List<String> sourceWordsToTranslate = JasonDictionaryUtil.filterRealWords(sourceWords);

    List<String> existingWordsLines = JasonFileUtil.readFileFromClasspathIntoWord(Dictionary_File);

    List<String> existingWords = JasonDictionaryUtil.getWords(existingWordsLines);

    sourceWordsToTranslate.removeIf(existingWords::contains);

    List<String> limitedWords = sourceWordsToTranslate.stream().limit(500).collect(Collectors.toList());

    PageSpider<String> spider = new WordTranslationSpider(limitedWords);

    List<String> result = spider.crawl();

    String newWordTranslation = result.stream().distinct().collect(Collectors.joining(System.lineSeparator()));
    // newWordTranslation = StringUtils.join(newWordTranslation, System.lineSeparator());
    JasonFileUtil.appendToFile(Target_Dictionary_File, newWordTranslation);

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
