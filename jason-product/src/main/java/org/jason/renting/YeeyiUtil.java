package org.jason.renting;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.jason.util.JasonFileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YeeyiUtil {
  private static final String ELEMENT_SELECTOR = "#qtcShow .qtc li";
  private static final String SHORT_DESC = ".ptxt .zdtxt";
  private static final String ATTRS = ".ptxt span";
  private static final String RELEASE_TIME_SELECTOR = ".num p:not.colorblod";
  private static final String ADDRESS_SELECTOR = ".ptxt p:last-child";
  
  
  public static List<RentingDataModel> toDataModel(Document document) {
Elements elements = document.select(ELEMENT_SELECTOR);
    
    for(Element current : elements) {
      String shortDescription = current.selectFirst(SHORT_DESC).text();
      System.out.println(shortDescription);
    }
    return null;
  }
  
  public static void main(String ... args) throws IOException {
    Path path = Paths.get("/home/jason/projects/jason-technology/jason-product/src/main/resources/house_search/yeeyi.html");
    List<String> lines = Files.readAllLines(path, Charset.forName("GB18030"));
    String htmlContent = lines.stream().collect(Collectors.joining());
    Document document = Jsoup.parse(htmlContent);
    Elements elements = document.select(ELEMENT_SELECTOR);
    
    for(Element current : elements) {
      String shortDescription = current.selectFirst(SHORT_DESC).text();
      //String releaseTime = current.selectFirst(RELEASE_TIME_SELECTOR).text();
      Elements attributeElements = current.select(ATTRS);
      String source = attributeElements.get(1).text();
      String rentingStyle = attributeElements.get(2).text();
      String houseStyle = attributeElements.get(3).text();
      String address = current.selectFirst(ADDRESS_SELECTOR).text();
      
      RentingDataModel model = RentingDataModel.build().source(source).rentingStyle(rentingStyle).houseStyle(houseStyle).address(address).shortDescription(shortDescription);
      System.out.println(ReflectionToStringBuilder.toString(model));
    }
    
  }
  
  
}
