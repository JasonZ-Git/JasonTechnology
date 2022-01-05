package org.jason.dictionary.translation.aws;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    for (SQSMessage message : messages) {
      String word = message.getBody();
      List<String> translations = getTranslations(word);
      writeToDB(word, translations);
    }
    return null;
  }

  private void writeToDB(String word, List<String> translations) {
    // TODO Auto-generated method stub

  }

  private List<String> getTranslations(String word) {
    // TODO
    return null;
  }
}
