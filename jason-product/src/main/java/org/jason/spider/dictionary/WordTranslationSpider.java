package org.jason.spider.dictionary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Unfinished;
import org.jason.spider.PageSpider;

/**
 * Version 1 - With shared pool - soft limit the number of the ThreadDriver; Version 2 - There should be hard way to limit the WebDriver number and block for new ones to come
 * 
 * @author Jason Zhang
 */
@Unfinished(todo = "Implement Version 2")
public class WordTranslationSpider implements PageSpider<TranslationResult> {

  private List<String> wordsToTranslate;
  private static final Logger logger = LogManager.getLogger();

  private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;

  public WordTranslationSpider(@Nonnull List<String> toTranslate) {
    Objects.requireNonNull(toTranslate);

    this.wordsToTranslate = toTranslate;
  }

  @Override
  public List<TranslationResult> crawl() throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    List<WordTranslationSpiderTask> translationTasks = new ArrayList<>();
    for (String word : this.wordsToTranslate) {
      WordTranslationSpiderTask wordTranslationTask = new WordTranslationSpiderTask(word);
      translationTasks.add(wordTranslationTask);
    }

    List<Future<TranslationResult>> translations = new ArrayList<>();
    try {
      translations = executor.invokeAll(translationTasks);
    } catch (InterruptedException e) {
      logger.warn(e);
    }

    List<TranslationResult> result = new ArrayList<>();
    for (Future<TranslationResult> current : translations) {
      try {
        result.add(current.get());
      } catch (InterruptedException | ExecutionException e) {
        logger.warn(e);
      }
    }

    executor.shutdown();

    return result;
  }
}
