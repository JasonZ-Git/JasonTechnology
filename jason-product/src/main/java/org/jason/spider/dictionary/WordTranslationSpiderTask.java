package org.jason.spider.dictionary;

import java.util.concurrent.Callable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WordTranslationSpiderTask implements Callable<String> {

  private static final String English_To_Chinese_Google_Translation = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=";

  private static final String Word_Translation_Format = "%s=%s";
  
  private String word;

  static {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
  }


  public WordTranslationSpiderTask(String word) {
    this.word = word;
  }

  @Override
  public String call() throws Exception {

    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    
    WebDriver driver = new ChromeDriver();

    driver.get(English_To_Chinese_Google_Translation + this.word);

    WebElement googleTranslationElement = driver.findElement(By.className("tlid-translation"));

    final String translation = googleTranslationElement.getText();

    driver.quit();

    return String.format(Word_Translation_Format, word, translation);
  }

}
