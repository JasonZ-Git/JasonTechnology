package org.jason.renting;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;


public class RentingDesktopApplication {
  
  private static final List<String> DEFAULT_AREA = Arrays.asList("toorak", "south yarra", "glen iris", "prahran", "richmond", "hawthorn", "st kilda");
  private static final String BasicURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1&";

  public static void main(String[] args) throws IOException {
    printAll();
  }

  public static void printAll() throws IOException {

    for (String current : DEFAULT_AREA) {
      String extended = YeeyiCriteria.build().district(current).toString();
      String url = BasicURL + extended;
      Document document = WebCrawlUtil.crawlPage(url);

      List<RentingDataModel> items = YeeyiUtil.toDataModel(document);

      List<RentingDataModel> filteredItems = YeeyiUtil.defaultFilter(items);
      
      String result = filteredItems.stream().map(item -> item.toString()).collect(Collectors.joining("\n"));

      System.out.println("=====" + current + "=====");
      System.out.println(result);
      System.out.println();
    }
  }
}
