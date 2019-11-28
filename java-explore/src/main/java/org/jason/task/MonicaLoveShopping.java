package org.jason.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jason.util.WebCrawlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MonicaLoveShopping {


  public static void main(String[] args) throws InterruptedException {
    Map<String, Integer> pagesWithPrice = getAllPagesWithLowPrice();
    report(pagesWithPrice);
  }


  public static void report(Map<String, Integer> pageWithPrices) throws InterruptedException {
    Map<String, Integer> sortedMap = sortMap(pageWithPrices);
    Integer count = 0;
    for (Entry<String, Integer> mapping : sortedMap.entrySet()) {
      if (count++ > 20)
        break;
      TimeUnit.SECONDS.sleep(2);
      System.out.println("minumum price " + mapping.getValue() + " found on page " + mapping.getKey());
    }
  }

  public static Map<String, Integer> getAllPagesWithLowPrice() throws InterruptedException {

    List<String> potentialPages = new ArrayList<>();
    Map<String, Integer> potentialPagesMap = new HashMap<String, Integer>();
    try {

      List<String> lines = FileUtils.readLines(new File("/home/jason/projects/jason-technology/java-explore/src/main/java/org/jason/task/pages.txt"));

      System.out.println("There are " + lines.size() + " pages");

      int scannedCount = 0;
      
      for (String currentPage : lines) {

        List<Integer> prices = selectPriceOfPage(currentPage);

        if (prices.isEmpty())
          continue;

        potentialPages.add(currentPage);
        potentialPagesMap.put(currentPage, prices.get(0));

        System.out.println("Have scanned " + ++scannedCount);
      }


    } catch (IOException e) {
      e.printStackTrace();
    }

    return potentialPagesMap;
  }

  public static Map<String, Integer> sortMap(Map<String, Integer> sourceMap) {
    Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String, Integer>>() {

      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        Integer v1 = e1.getValue();
        Integer v2 = e2.getValue();
        return v1.compareTo(v2);
      }
    };

    // Sort method needs a List, so let's first convert Set to List in Java
    List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(sourceMap.entrySet());

    // sorting HashMap by values using comparator
    Collections.sort(listOfEntries, valueComparator);

    LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

    // copying entries from List to Map
    for (Entry<String, Integer> entry : listOfEntries) {
      sortedByValue.put(entry.getKey(), entry.getValue());
    }

    return sortedByValue;
  }

  public static List<Integer> selectPriceOfPage(String sourePageURL) throws IOException {
    Document myDealPage = WebCrawlUtil.crawlPage("https://" + sourePageURL);

    String select2 = "itemprop[price]";// Arrays.asList("itemprop[price]", ".numericSpan .mainPriceSpan");
    //
    Elements result = myDealPage.select(".numericSpan .mainPriceSpan");
    List<Integer> prices = new ArrayList<>();

    for (Element current : result) {
      if (StringUtils.isNumeric(current.text())) {
        prices.add(Integer.valueOf(current.text()));

      }
    }

    return prices;

  }

}
