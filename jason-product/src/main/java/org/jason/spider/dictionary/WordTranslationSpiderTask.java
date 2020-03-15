package org.jason.spider.dictionary;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WordTranslationSpiderTask implements Callable<String> {

  private static final String English_To_Chinese_Google_Translation = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

  private static final String Word_Translation_Format = "%s=%s";

  private static Logger logger = LogManager.getLogger();

  private String word;


  static {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    // Turn off debug log
    System.setProperty("webdriver.chrome.verboseLogging", "false");
  }

  public WordTranslationSpiderTask(String word) {
    this.word = word;
  }

  @Override
  public String call() throws Exception {

    WebDriver driver = null;
    String translations = null;
    String primaryTranslation = "";
    try {
      driver = new ChromeDriver();

      driver.get(English_To_Chinese_Google_Translation + this.word);

      WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

      primaryTranslation = googleTranslationElement.getText();
      
      if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation)) throw new Exception("No Translation Found for word " + word);

      WebElement translationsRoot = driver.findElement(By.cssSelector(".gt-cd.gt-cd-mbd.gt-cd-baf"));
      
      List<WebElement> translationElements = translationsRoot.findElements(By.cssSelector(".gt-baf-cell.gt-baf-word-clickable"));

      translations = translationElements.stream().map(WebElement::getText).filter(StringUtils::isNotBlank).collect(Collectors.joining(","));

    } catch (Exception e) {
      logger.warn("Error fetching translation for " + word, e);
      
      throw e;
      
    } finally {
      driver.quit();
    }

    return String.format(Word_Translation_Format, word, getTranslation(translations, primaryTranslation));
  }

  private String getTranslation(String translations, @Nonnull String primaryTranslation) {
    Objects.requireNonNull(primaryTranslation);
    
    if (StringUtils.isBlank(translations)) return primaryTranslation;
    
    if (translations.contains(primaryTranslation)) return translations;
    
    return primaryTranslation;
    
  }
  
}
