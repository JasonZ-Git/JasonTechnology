package org.jason.test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JasonDistionaryTest {

  @Test
  public void selieumTest () {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    WebDriver driver  = new ChromeDriver();
    driver.get("https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=world");
    String appTitle = driver.getTitle();
    System.out.println("Application title is :: "+appTitle);
    driver.quit();
  }
}
