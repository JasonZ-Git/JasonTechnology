package org.jason.jaxb.fromXML;

import java.util.List;

public class ReadXmlMain {
  public static void main(String args[]) {
    StaXParser read = new StaXParser();
    List<Item> readConfig = read.readConfig("src/main/java/org/jason/jaxb/fromXML/config.xml");
    for (Item item : readConfig) {
      System.out.println(item);
    }
  }
}
