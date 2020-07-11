package org.jason.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JasonDistionaryTest {

    private static WebDriver driver;
    private static final String Google_Translate = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void selieumTest() {
        String toTranslate = "world";
        String translation = googleTranslate("world");

        System.out.printf("The translation for %s is %s", toTranslate, translation);
    }

    public static String googleTranslate(String wordToTranslate) {
        driver.get(Google_Translate + wordToTranslate);

        WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

        final String translation = googleTranslationElement.getText();

        driver.quit();

        return translation;

    }
}
