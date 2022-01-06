package org.jason.dictionary.translation.aws;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.util.GoogleTranslationUtil;
import org.junit.jupiter.api.Test;

public class GoogleTranslationUtilTest {

  private static Logger logger = LogManager.getLogger();
  
  @Test
  public void testTranslation() {
    Set<String> translations = GoogleTranslationUtil.getTranslation("hello");
    
    logger.info(translations);
  }
}
