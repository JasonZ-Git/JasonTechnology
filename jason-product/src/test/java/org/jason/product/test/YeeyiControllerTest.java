package org.jason.product.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.spider.yeeyi.YeeyiCriteria;
import org.jason.spider.yeeyi.YeeyiUtil;
import org.jason.spider.yeeyi.controller.RentingVO;
import org.jason.util.WebCrawlUtil;
import org.jason.util.exception.PageNotFoundException;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class YeeyiControllerTest {

  private static String basicURL = "http://www.yeeyi.com/forum/index.php?app=forum&act=display&fid=142&rcity1=1&";



  @Test
  public void testParse() throws IOException, URISyntaxException, PageNotFoundException {
    List<String> areas = Arrays.asList("south yarra", "glen iris", "prahran", "richmond", "hawthorn", "st kilda");

    for (String current : areas) {
      String extended = YeeyiCriteria.build().district(current).toString();
      String url = basicURL + extended;
      Document document = WebCrawlUtil.crawlPage(url);

      List<RentingVO> items = YeeyiUtil.toRentingVO(document);

      String result = items.stream().filter(item -> !item.getShortDescription().contains("短租")).limit(10).map(item -> item.toString()).collect(Collectors.joining("\n"));

      System.out.println("=====" + current + "=====");
      System.out.println(result);
      System.out.println();
      System.out.println();
    }

  }
}
