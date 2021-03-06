package org.jason.dictionary.helper;

import com.jayway.jsonpath.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.util.dictionary.JasonDictionaryAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class GooglePronunciationHelper {

    private static final String English_To_Chinese_Google_Translation = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

    private static final String DOWNLOAD_VEDIO_FORMAT = "downloadvedio -o %s%s.mp3 '%s'";

    private static Logger logger = LogManager.getLogger();

    static {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        // Turn off debug log
        System.setProperty("webdriver.chrome.verboseLogging", "false");
    }

    public static URL getPronunciationURL(String word) {

        URL pronunciationURL = null;
        WebDriver driver = null;

        try {
            driver = getWebDriver();

            driver.get(English_To_Chinese_Google_Translation + word);

            pronunciationURL = getWordPronounceURL(driver);

            destroyDriver(driver);

        } catch (Exception e) {
            logger.warn("No translation found for " + word, e);
            destroyDriver(driver);

            return null;
        }

        return pronunciationURL;
    }

    public static Path downLoadGooglePronunciation(String word) {
        URL pronunciation = getPronunciationURL(word);

        ProcessBuilder processBuilder = new ProcessBuilder();

        String downloadVedioCommand = String.format(DOWNLOAD_VEDIO_FORMAT, JasonDictionaryAPI.NEW_PRONUNCIATION_DIR, word, pronunciation.toExternalForm());
        processBuilder.command("sh", "-c", downloadVedioCommand);

        try {
            Process process = processBuilder.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

        return Paths.get(JasonDictionaryAPI.NEW_PRONUNCIATION_DIR, word + ".mp3");
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

    private static void destroyDriver(WebDriver driver) {
        driver.quit();
    }

    private static WebDriver getWebDriver() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.chrome();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("w3c", false);
        options.merge(caps);

        WebDriver driver = new RemoteWebDriver(new URL("http://101.116.233.50:4444/wd/hub"), options);
        //WebDriver driver = new ChromeDriver(options);

        return driver;
    }

}
