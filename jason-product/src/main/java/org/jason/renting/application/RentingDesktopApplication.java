package org.jason.renting.application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.mail.EmailException;
import org.jason.renting.controller.RentingVO;
import org.jason.renting.utils.YeeyiCriteria;
import org.jason.renting.utils.YeeyiUtil;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;


public class RentingDesktopApplication {

  private static final List<String> DEFAULT_AREA = Arrays.asList("camberwell","Burwood","surrey hill", "caulfield", "malvern", "Toorak", "south yarra", "glen iris", "richmond", "hawthorn");

  private static final String BasicURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1&";

  private static final List<String> MAIL_TO_SEND = Arrays.asList("zclok236@gmail.com", "thfanna@gmail.com");
  
  public static void main(String[] args) throws IOException, InterruptedException, EmailException {
    printAll();
  }
  
  public static void printAll() throws IOException, InterruptedException, EmailException {

    for (String current : DEFAULT_AREA) {
      String extended = YeeyiCriteria.build().district(current).getSearchCriteria();
      String url = BasicURL + extended;
      Document document = WebCrawlUtil.crawlPage(url);

      List<RentingVO> items = YeeyiUtil.toRentingVO(document);

      List<RentingVO> filteredItems = YeeyiUtil.defaultFilter(items);
      
      filteredItems = filteredItems.stream().filter(item -> Integer.parseInt(item.getHouseStyle().substring(0, 1)) <= 4).collect(Collectors.toList());

      String content = filteredItems.stream().map(item -> item.toString()).collect(Collectors.joining("\n"));

      StringBuilder builder = new StringBuilder();
      builder.append("=====" + current + "=====").append("\n");
      builder.append(content).append("\n\n");
      TimeUnit.SECONDS.sleep(2);
      
      System.out.println(builder.toString());
    }
    
    /*for (String current : MAIL_TO_SEND) {
      JasonMailUtil.sendMail(current, result);
      System.out.println("Send to Mail: " + current);
    }*/
  }
}
