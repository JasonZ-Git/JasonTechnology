package org.jason.spider.dictionary;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Unfinished;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.jayway.jsonpath.JsonPath;

@Unfinished(todo = "Implement Version 2 from WordTranslationSpider")
public class WordTranslationSpiderTask implements Callable<TranslationResult> {

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
  public TranslationResult call() {

    WebDriver driver = null;
    String translations = "";
    URL pronouceURL = null;

    try {
      driver = getWebDriver();

      driver.get(English_To_Chinese_Google_Translation + this.word);

      translations = getWordTranslation(driver);

      pronouceURL = getWordPronounceURL(driver);
      
      pushToPoolOrDestroy(driver);

    } catch (Exception e) {
      logger.warn("No translation found for " + word, e);
      driver.quit();
      
      return TranslationResult.build(this.word, null, null);
    }

    return TranslationResult.build(this.word, translations, pronouceURL);
  }
  
  private URL getWordPronounceURL(WebDriver driver) throws MalformedURLException {
    WebElement englishPronourceButton = driver.findElement(By.cssSelector(".src-tts.left-positioned.ttsbutton.jfk-button-flat.source-or-target-footer-button.jfk-button"));
    englishPronourceButton.click();
    
    final String MEDIA_TYPE_FILTER = "\"type\":\"Media\"";
    final String URL_FILTER = "\"url\":";
    
    List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
    
    List<String> mediaTypeLogs = entries.parallelStream().map(item -> item.getMessage()).filter(item -> item.contains(MEDIA_TYPE_FILTER))
    .filter(item -> item.contains(URL_FILTER)).collect(Collectors.toList());
    
    
    for (String current : mediaTypeLogs) {
        String url = JsonPath.read(current, "$.message.params.request.url");
        if (url != null) {
          return new URL(url);
        }
    }
    
    return null;
    
  }

  private String getWordTranslation(WebDriver driver) {
    WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

    String primaryTranslation = googleTranslationElement.getText();

    if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation)) {
      logger.warn("No Translation Found for {} ", word);
      return "";
    }

    WebElement translationsRoot = driver.findElement(By.cssSelector(".gt-cd.gt-cd-mbd.gt-cd-baf"));

    List<WebElement> translationElements = translationsRoot.findElements(By.cssSelector(".gt-baf-cell.gt-baf-word-clickable"));

    String translations =  translationElements.stream().map(WebElement::getText).filter(StringUtils::isNotBlank).collect(Collectors.joining(","));
  
    return getTranslation(translations, primaryTranslation);
  }

  private void pushToPoolOrDestroy(WebDriver driver) {
    if (webDriverPool.size() <= 10) {
      webDriverPool.add(driver);
    } else {
      driver.quit();
    }
  }

  private WebDriver getWebDriver() {

    DesiredCapabilities caps = DesiredCapabilities.chrome();
    
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
    
    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    
    ChromeOptions options = new ChromeOptions();
    options.merge(caps);
    
    WebDriver driver = webDriverPool.isEmpty() ? new ChromeDriver(options) : webDriverPool.poll();

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
