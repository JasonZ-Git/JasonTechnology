package org.jason.dictionary.translation.aws.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.model.DictionaryItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class GoogleTranslationUtil {

  private static final String English_To_Chinese_Google_Translation =
      "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

  private static Logger logger = LogManager.getLogger();

  static {
    System.setProperty("webdriver.chrome.driver","/opt/chromedriver");

    // Turn off debug log
    System.setProperty("webdriver.chrome.verboseLogging", "false");
  }

  private GoogleTranslationUtil() {
    //throw new RuntimeException("No instance for you");
  }

  public static Set<String> getTranslation(String word) {

    WebDriver driver = null;
    Set<String> translations = new HashSet<>();

    try {
      logger.info("translation fetched for {} is {}", word, translations);
      
      driver = getWebDriver();

      driver.get(English_To_Chinese_Google_Translation + word);

      translations = getWordTranslation(driver, word);
      
      logger.info("translation fetched for {} is {}", word, translations);

      destroyDriver(driver);

    } catch (Exception e) {
      logger.warn("No translation found for " + word, e);
      destroyDriver(driver);
      return translations;
    }

    return translations;
  }

  public static Set<DictionaryItem> getTranslations(List<String> words) {

    Set<DictionaryItem> wordTranslations = new HashSet<>();

    for (String current : words) {
      Set<String> translations = getTranslation(current);

      wordTranslations.add(DictionaryItem.of(current, translations));

      sleepQuietly();
    }

    return wordTranslations;
  }

  private static void sleepQuietly() {
    try {
      TimeUnit.MILLISECONDS.sleep(500);
    } catch (Exception e) {
      logger.error(e);

      throw new RuntimeException(e);
    }
  }

  private static Set<String> getWordTranslation(WebDriver driver, String word) {
    WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

    String primaryTranslation = googleTranslationElement.getText();

    if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation)) {
      logger.warn("No Translation Found for {} ", word);
      return Collections.emptySet();
    }

    WebElement translationsRoot = driver.findElement(By.cssSelector(".gt-cd.gt-cd-mbd.gt-cd-baf"));

    List<WebElement> translationElements =
        translationsRoot.findElements(By.cssSelector(".gt-baf-cell.gt-baf-word-clickable"));

    List<String> translations =
        translationElements.stream()
            .map(WebElement::getText)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());

    return getTranslation(primaryTranslation, translations);
  }

  private static void destroyDriver(WebDriver driver) {
    driver.quit();
  }

  private static WebDriver getWebDriver() {
    logger.info("Start Creating Chrome Drive");
    
    ChromeOptions options = new ChromeOptions();
    options.setBinary("/opt/headless-chromium");
    options.addArguments(
        "--headless", "--no-sandbox", "--single-process", "--disable-dev-shm-usage");

    WebDriver driver = new ChromeDriver(options);
    //WebDriver driver = new HtmlUnitDriver();
    logger.info("Chrome Drive Created");
    

    return driver;
  }

  private static Set<String> getTranslation(String primaryTranslation, List<String> secondTranslations) {
    Objects.requireNonNull(primaryTranslation);
    Set<String> translations = new HashSet<>();
    translations.add(primaryTranslation);

    if (secondTranslations.isEmpty()) {
      return translations;
    }

    if (translations.contains(primaryTranslation)) {
      translations.clear();

      translations.addAll(secondTranslations);
    }

    return translations;
  }
}
