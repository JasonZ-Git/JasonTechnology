package org.jason.renting;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.mail.EmailException;
import org.jason.util.WebCrawlUtil;
import org.jason.util.mail.JasonMailUtil;
import org.jsoup.nodes.Document;


public class RentingDesktopApplication {

  private static final List<String> DEFAULT_AREA = Arrays.asList("caulfield north", "elwood", "malvern", "toorak", "south yarra", "glen iris", "prahran", "richmond", "hawthorn", "st kilda");

  private static final String BasicURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1&";

  private static final List<String> MAIL_TO_SEND = Arrays.asList("zclok236@gmail.com", "thfanna@gmail.com");
  
  public static void main(String[] args) throws IOException, InterruptedException, EmailException {
    printAll();
  }
  
  public static void printAll() throws IOException, InterruptedException, EmailException {

    String result = "";
    for (String current : DEFAULT_AREA) {
      String extended = YeeyiCriteria.build().district(current).getSearchCriteria();
      String url = BasicURL + extended;
      Document document = WebCrawlUtil.crawlPage(url);

      List<RentingDataModel> items = YeeyiUtil.toDataModel(document);

      List<RentingDataModel> filteredItems = YeeyiUtil.defaultFilter(items);

      String content = filteredItems.stream().map(item -> item.toString()).collect(Collectors.joining("\n"));

      StringBuilder builder = new StringBuilder();
      builder.append("=====" + current + "=====").append("\n");
      builder.append(content).append("\n\n");

      result += builder.toString();

      System.out.println(builder.toString());
    }
    
    for (String current : MAIL_TO_SEND) {
      JasonMailUtil.sendMail(current, result);
      System.out.println("Send to Mail: " + current);
    }
  }
}
