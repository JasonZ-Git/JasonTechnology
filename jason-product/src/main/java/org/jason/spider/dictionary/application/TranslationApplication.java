package org.jason.spider.dictionary.application;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.jason.annotation.Application;
import org.jason.spider.PageSpider;
import org.jason.spider.dictionary.WordTranslationSpider;
import org.jason.util.JasonFileUtil;
import org.jason.util.translation.JasonDictionaryUtil;

@Application(name = "Translation Application")
public class TranslationApplication {

  private static final String SourceFile = "dictionary/source.txt";

  private static final String Dictionary_File = "dictionary/dictionary.properties";

  private static final String temp_dictionary_file = "/home/jason/projects/jason-technology/jason-product/src/main/resources/dictionary/dictionary.properties";

  public static void main(String[] args) throws IOException {

    List<String> sourceWords = JasonFileUtil.readFileFromClasspathIntoWord(SourceFile);

    List<String> realWords = JasonDictionaryUtil.filterRealWords(sourceWords);

    List<String> existingWordsLines = JasonFileUtil.readFileFromClasspathIntoWord(Dictionary_File);

    List<String> existingWords = JasonDictionaryUtil.getWords(existingWordsLines);

    System.out.println("Existing Dictionary Words: " + existingWords);

    realWords.removeIf(existingWords::contains);

    System.out.printf("Total Words to translate %s - %s ", realWords.size(), realWords);

    PageSpider<String> spider = new WordTranslationSpider(realWords);

    List<String> result = spider.crawl();

    String newWordTranslation = result.stream().distinct().collect(Collectors.joining(System.lineSeparator()));
    // newWordTranslation = StringUtils.join(newWordTranslation, System.lineSeparator());
    JasonFileUtil.appendToFile(temp_dictionary_file, newWordTranslation);

    System.out.println(result);
  }

}
