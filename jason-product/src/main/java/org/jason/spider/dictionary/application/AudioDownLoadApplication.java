package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.annotation.Application;
import org.jason.spider.dictionary.DictionaryConstants;
import org.jason.util.JasonThreadUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

@Application(name = "Translation Application")
public class AudioDownLoadApplication {

  private static final Logger logger = LogManager.getLogger();

  static {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
    // Turn off debug log
    System.setProperty("webdriver.chrome.verboseLogging", "false");
  }

  public static void main(String[] args) throws IOException, URISyntaxException {
    downloadAudio();
  }

  private static void downloadAudio() throws IOException, URISyntaxException {
    final String DEMO_URL = "https://translate.google.com/translate_tts?ie=UTF-8&q=mix&tl=en&total=1&idx=0&textlen=3&tk=91477.512718&client=webapp&prev=input";
    WebDriver driver = getWebDriver("chrome");

    driver.navigate().to(DEMO_URL);

    new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, "s")).build().perform();

    new Actions(driver).keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).build().perform();
    
    JasonThreadUtil.sleepQuietlyInSeconds(5);
    
    driver.quit();
  }

  private static WebDriver getWebDriver(String exployerType) {
    if (StringUtils.equals("firefox", exployerType))
      return getFirefoxWebDriver();

    return getChromeWebDriver();
  }

  private static WebDriver getChromeWebDriver() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");

    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", DictionaryConstants.PRONOUNCE_DIR);
    options.setExperimentalOption("prefs", chromePrefs);


    WebDriver driver = new ChromeDriver(options);

    return driver;
  }

  private static WebDriver getFirefoxWebDriver() {
    WebDriver driver = new FirefoxDriver();
    return driver;
  }

}
