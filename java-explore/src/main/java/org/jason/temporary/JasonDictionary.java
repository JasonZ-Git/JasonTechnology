package org.jason.temporary;

import java.io.IOException;
import org.jason.util.SpiderUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JasonDictionary {

  public static void main(String[] args) {

    //googleDictionary();
    selieumTest();

  }
  
  public static void googleDictionary() {

    final String COMMON_URL = "https://translate.google.cn/#view=home&op=translate&sl=auto&tl=zh-CN&text=%s";
    
    final String rource_world = "world";
    
    final String world_query =  String.format(COMMON_URL, rource_world);
    
    try {
      Document result = SpiderUtil.crawlPage_Java8("https://translate.google.cn/#view=home&op=translate&sl=auto&tl=zh-CN&text=world");
      
      Element translate = result.selectFirst(".tlid-result .text-wrap .tlid-translation");
      
      System.out.println(result);
      
      System.out.println(translate);
    } catch (IOException e) {
    }
  }
  
  public static void selieumTest () {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    WebDriver driver  = new ChromeDriver();
    driver.get("http://www.amazon.com");
    String appTitle = driver.getTitle();
    System.out.println("Application title is :: "+appTitle);
    driver.quit();
  }
  
  
}
