package org.jason.dictionary.translation.aws.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

/**
 * This helper class requires chromedriver and headless-chrome to be available on path.
 * 
 * This is working well with local, however it is not working well on lambda - Will use the python version for now.
 * 
 * @TODO - Figure out why binary chrome not working - probably lost some default library - may need a new layer
 * @author Jason Zhang
 */
public class GoogleTranslationHelper {

  private static final String English_To_Chinese_Google_Translation =
      "https://translate.google.com/?sl=en&tl=zh-CN&text=%s&op=translate";

  private static final By MAIN_TRANSLATION_SELECTOR =
      By.cssSelector("span[data-language-for-alternatives='zh-CN'] > span");

  private static final By TRANSLATION_LIST_SELECTOR = By.cssSelector("span[data-term-type='tl']");

  private static final String HEADLESS_CHROME = "/opt/headless-chromium_86";
  private static final String CHROME_DRIVE_PATH = "/opt/chromedriver_86";

  private static Logger logger = LogManager.getLogger(GoogleTranslationHelper.class);

  public static Set<String> getTranslation(String word) {

    System.setProperty("webdriver.chrome.driver", CHROME_DRIVE_PATH);
    
    WebDriver driver = null;
    Set<String> translations = new HashSet<>();

    try {
      logger.info("translation fetch started for word {}", word);

      driver = getWebDriver();

      driver.get(String.format(English_To_Chinese_Google_Translation, word));

      sleepQuietly();

      translations = getWordTranslation(driver, word);

      logger.info("translation fetched for {} is {}", word, translations);

    } catch (Exception e) {
      logger.warn("No translation found for " + word, e);
      return translations;

    } finally {
      driver.quit();
    }

    return translations;
  }

  public static Set<DictionaryItem> getTranslations(List<String> words) {

    Set<DictionaryItem> wordTranslations = new HashSet<>();

    for (String current : words) {

      Set<String> translations = getTranslation(current);

      wordTranslations.add(DictionaryItem.of(current, translations));
    }

    return wordTranslations;
  }

  private static void sleepQuietly() {
    try {
      TimeUnit.SECONDS.sleep(4);
    } catch (Exception e) {
      logger.error(e);

      throw new RuntimeException(e);
    }
  }

  private static Set<String> getWordTranslation(WebDriver driver, String word) {

    String primaryTranslation = findMainTranslation(driver);

    if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation)) {
      logger.warn("No Translation Found for {} ", word);
      return Collections.emptySet();
    }

    List<WebElement> translationElements = driver.findElements(TRANSLATION_LIST_SELECTOR);

    List<String> translations =
        translationElements.stream()
            .map(WebElement::getText)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());

    return getTranslation(primaryTranslation, translations);
  }

  private static String findMainTranslation(WebDriver driver) {
    try {
      WebElement mainTranslation = driver.findElement(MAIN_TRANSLATION_SELECTOR);
      if (mainTranslation != null) return mainTranslation.getText();
    } catch (Exception ex) {
      logger.error("Cannot find main translation for word", ex);
    }
    return StringUtils.EMPTY;
  }

  private static WebDriver getWebDriver() {
    logger.info("Start Creating Chrome Drive");

    ChromeOptions options = new ChromeOptions();
    options.setBinary(HEADLESS_CHROME);
    options.setHeadless(true);

    WebDriver driver = new ChromeDriver(options);
    logger.info("Chrome Drive Created");

    return driver;
  }

  private static Set<String> getTranslation(
      String primaryTranslation, List<String> secondTranslations) {
    Objects.requireNonNull(primaryTranslation);

    Set<String> translations = new LinkedHashSet<>();
    translations.add(primaryTranslation);

    if (secondTranslations.isEmpty()) {
      return translations;
    }

    if (translations.contains(primaryTranslation)) {
      secondTranslations.remove(primaryTranslation);

      translations.addAll(secondTranslations);
    }

    return translations;
  }
}
