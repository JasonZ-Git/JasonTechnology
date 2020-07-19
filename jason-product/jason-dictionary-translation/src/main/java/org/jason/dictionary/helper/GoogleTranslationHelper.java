package org.jason.dictionary.helper;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class GoogleTranslationHelper {

    private static final String English_To_Chinese_Google_Translation = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

    private static Logger logger = LogManager.getLogger();

    static {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        // System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        // Turn off debug log
        System.setProperty("webdriver.chrome.verboseLogging", "false");
    }

    public static WordTranslation getTranslation(String word) {

        WebDriver driver = null;
        String translations = "";
        URL pronouceURL = null;

        try {
            driver = getWebDriver();

            driver.get(English_To_Chinese_Google_Translation + word);

            translations = getWordTranslation(driver, word);

            pronouceURL = getWordPronounceURL(driver);

            destroyDriver(driver);

        } catch (Exception e) {
            logger.warn("No translation found for " + word, e);
            destroyDriver(driver);

            return WordTranslation.build(word, null);
        }

        return WordTranslation.build(word, translations);
    }


    public static List<WordTranslation> getTranslations(List<String> words) {

        List<WordTranslation> wordTranslations = new ArrayList<>();

        for (String current : words) {
            WordTranslation translation = getTranslation(current);

            wordTranslations.add(translation);

            sleepQuietly();
        }

        return wordTranslations;
    }

    private static void sleepQuietly(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            logger.error(e);

            throw new RuntimeException(e);
        }
    }

    private static URL getWordPronounceURL(WebDriver driver) throws MalformedURLException {
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

    private static String getWordTranslation(WebDriver driver, String word) {
        WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

        String primaryTranslation = googleTranslationElement.getText();

        if (StringUtils.isBlank(primaryTranslation) || word.equalsIgnoreCase(primaryTranslation)) {
            logger.warn("No Translation Found for {} ", word);
            return "";
        }

        WebElement translationsRoot = driver.findElement(By.cssSelector(".gt-cd.gt-cd-mbd.gt-cd-baf"));

        List<WebElement> translationElements = translationsRoot.findElements(By.cssSelector(".gt-baf-cell.gt-baf-word-clickable"));

        String translations = translationElements.stream().map(WebElement::getText).filter(StringUtils::isNotBlank).collect(Collectors.joining(","));

        return getTranslation(translations, primaryTranslation);
    }

    private static void destroyDriver(WebDriver driver) {
        driver.quit();
    }

    private static WebDriver getWebDriver() {

        DesiredCapabilities caps = DesiredCapabilities.chrome();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeOptions options = new ChromeOptions();
        options.merge(caps);

        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    private static String getTranslation(String translations, @Nonnull String primaryTranslation) {
        Objects.requireNonNull(primaryTranslation);

        if (StringUtils.isBlank(translations))
            return primaryTranslation;

        if (translations.contains(primaryTranslation))
            return translations;

        return String.format("%s,%s", primaryTranslation, translations);

    }

}
