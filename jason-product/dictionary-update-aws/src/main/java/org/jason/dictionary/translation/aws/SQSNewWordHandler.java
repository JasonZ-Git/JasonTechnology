package org.jason.dictionary.translation.aws;

import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.util.GoogleTranslationHelper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

/**
 * Read SQS message, get translation from internet, save it to DB
 *
 * @author Jason Zhang
 */
public class SQSNewWordHandler implements RequestHandler<SQSEvent, Void> {

  private static final Logger logger = LogManager.getLogger(SQSNewWordHandler.class);

  @Override
  public Void handleRequest(SQSEvent sqsEvent, Context context) {
    List<SQSMessage> messages = sqsEvent.getRecords();
    logger.info("message total size is {}", messages.size());

    for (SQSMessage message : messages) {
      String word = message.getBody();
      logger.info("Word is {}", word);
      Set<String> translations = GoogleTranslationHelper.getTranslation(word);
      logger.info("Transation is {}", translations);
      writeToDB(word, translations);
    }

    return null;
  }

  private void writeToDB(String word, Set<String> translations) {
    // TODO Auto-generated method stub
  }
}
