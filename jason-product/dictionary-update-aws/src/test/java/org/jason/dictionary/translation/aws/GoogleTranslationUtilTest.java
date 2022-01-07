package org.jason.dictionary.translation.aws;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.util.GoogleTranslationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoogleTranslationUtilTest {

  private static Logger logger = LogManager.getLogger();
  
  @Test
  public void testTranslation() {
    Set<String> translations = GoogleTranslationHelper.getTranslation("hello");
    
    logger.info(translations);
    
    Assertions.assertTrue(translations.contains("你好"));
  }
}
