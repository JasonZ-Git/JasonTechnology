package org.jason.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class JasonDictionary_HeaderLess {

  private static final String Google_Translate_URL = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=world";
  
  @Ignore
  public void htmlUnit_Test() throws Exception {
      try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
          final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
          Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

//          final String pageAsXml = page.asXml();
//          Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//          final String pageAsText = page.asText();
//          Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
      }
  }
  
  @Ignore
  public void googleTranslate_Test() throws Exception {
      try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
          final HtmlPage page = webClient.getPage(Google_Translate_URL);
          Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

          final String pageAsXml = page.asXml();
          Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

          final String pageAsText = page.asText();
          Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
      }
  }
  
  @Test
  public void headless_SeleniumTest (){ 
      
    HtmlUnitDriver driver = new HtmlUnitDriver();
    driver.setJavascriptEnabled(true); 
    driver.get("http://www.google.com");
     
    System.out.println("Title of the page is" + driver.getTitle());
     
    driver.get(Google_Translate_URL);
    System.out.println("Title of the page is" + driver.getTitle());
    
    //WebElement java = driver.findElement(By.name("q"));
    //java.sendKeys("Java Code Geeks");
    //java.submit();
  }
  
  @Ignore
  public void selieumTest () {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    WebDriver driver  = new ChromeDriver();
    driver.get("http://www.amazon.com");
    String appTitle = driver.getTitle();
    System.out.println("Application title is :: "+appTitle);
    driver.quit();
  }
  
  
}
