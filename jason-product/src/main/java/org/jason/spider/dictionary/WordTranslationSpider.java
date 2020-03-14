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
import org.jason.spider.PageSpider;

public class WordTranslationSpider implements PageSpider<String> {

  private List<String> wordsToTranslate;

  public WordTranslationSpider(@Nonnull List<String> toTranslate) {
    Objects.requireNonNull(toTranslate);
    
    this.wordsToTranslate = toTranslate;
  }

  @Override
  public List<String> crawl() throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(100);

    List<WordTranslationSpiderTask> translationTasks = new ArrayList<>();
    for (String word : this.wordsToTranslate) {
      WordTranslationSpiderTask wordTranslationTask = new WordTranslationSpiderTask(word);
      translationTasks.add(wordTranslationTask);
    }

    List<Future<String>> translations = new ArrayList<>();
    try {
      translations = executor.invokeAll(translationTasks);
    } catch (InterruptedException e) {
      System.out.println(e);
    }


    List<String> result = new ArrayList<>();
    for (Future<String> current : translations) {
      try {
        result.add(current.get());
      } catch (InterruptedException | ExecutionException e) {
        System.out.println(e);
      }
    }
    
    executor.shutdown();

    return result;
  }
}
