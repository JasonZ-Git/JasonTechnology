package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.util.List;
import org.jason.annotation.Application;
import org.jason.spider.PageSpider;
import org.jason.spider.dictionary.WordTranslationSpider;
import org.jason.util.JasonFileUtil;
import org.jason.util.translation.JasonDictionaryUtil;

@Application(name = "Translation Application")
public class TranslationApplication {

  public static void main(String[] args) throws IOException {
    
    List<String> sourceWords = JasonFileUtil.readFileFromClasspathIntoWord("dictionary/source.txt");

    List<String> realWords = JasonDictionaryUtil.filterRealWords(sourceWords);
    
    List<String> existingWordsLines = JasonFileUtil.readFileFromClasspathIntoWord("dictionary/dictionary.properties");
    
    List<String> existingWords = JasonDictionaryUtil.getWords(existingWordsLines);
    
    realWords.removeIf(existingWords::contains);

    PageSpider<String> spider = new WordTranslationSpider(realWords);
    List<String> result =  spider.crawl();
    
    System.out.println(result);
  }

}
