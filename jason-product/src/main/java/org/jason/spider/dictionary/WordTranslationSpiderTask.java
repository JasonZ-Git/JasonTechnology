package org.jason.spider.dictionary;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Unfinished;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@Unfinished(todo = "Implement Version 2 from WordTranslationSpider")
public class WordTranslationSpiderTask implements Callable<String> {

  private static final String English_To_Chinese_Google_Translation = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

  private static final String Word_Translation_Format = "%s=%s";

  private static Logger logger = LogManager.getLogger();

  private Queue<WebDriver> webDriverPool;

  private String word;


  static {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    // Turn off debug log
    System.setProperty("webdriver.chrome.verboseLogging", "false");
  }

  public WordTranslationSpiderTask(Queue<WebDriver> webDriverPool, String word) {
    this.webDriverPool = webDriverPool;
    this.word = word;
  }

  @Override
  public String call() throws Exception {

    WebDriver driver = null;
    String translations = null;
    String primaryTranslation = "";

    // logger.info("Start task " + Thread.currentThread() + ": size of queue is " + blockingQueue.size());

    try {
      driver = getWebDriver();

      driver.get(English_To_Chinese_Google_Translation + this.word);

      WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

      primaryTranslation = googleTranslationElement.getText();

      if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation))
        throw new Exception("No Translation Found for word " + word);

      WebElement translationsRoot = driver.findElement(By.cssSelector(".gt-cd.gt-cd-mbd.gt-cd-baf"));

      List<WebElement> translationElements = translationsRoot.findElements(By.cssSelector(".gt-baf-cell.gt-baf-word-clickable"));

      translations = translationElements.stream().map(WebElement::getText).filter(StringUtils::isNotBlank).collect(Collectors.joining(","));

      pushToPoolOrDestroy(driver);

    } catch (Exception e) {
      logger.warn("Error fetching translation for " + word, e);
      driver.quit();

      throw e;
    }
    // logger.info("End task" + Thread.currentThread() + ": size of queue is " + blockingQueue.size());

    return String.format(Word_Translation_Format, word, getTranslation(translations, primaryTranslation));
  }

  private void pushToPoolOrDestroy(WebDriver driver) {
    if (webDriverPool.size() <= 10) {
      webDriverPool.add(driver);
    } else {
      driver.quit();
    }
  }
  
  private WebDriver getWebDriver() {

    WebDriver driver = webDriverPool.isEmpty() ? new ChromeDriver() : webDriverPool.poll();

    return driver;
  }

  private String getTranslation(String translations, @Nonnull String primaryTranslation) {
    Objects.requireNonNull(primaryTranslation);

    if (StringUtils.isBlank(translations))
      return primaryTranslation;

    if (translations.contains(primaryTranslation))
      return translations;

    return primaryTranslation;

  }

}
