package org.jason.dictionary.translation.aws;

import java.io.StringReader;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Get the translation and put it to DB
 *
 * <p>Currently, java version translation is not working well in lambda-java, but works well in
 * lambda-python.
 *
 * <p>This function is to receive the translation and make it available
 *
 * @author Jason Zhang
 */
public class PersistTranslationHandler implements RequestHandler<SNSEvent, String> {

  private static final Logger logger = LogManager.getLogger(PersistTranslationHandler.class);

  @Override
  public String handleRequest(SNSEvent snsMessage, Context context) {

    List<SNSRecord> records = snsMessage.getRecords();
    logger.info("There are  {} sns records in all", records.size());
    String message = records.get(0).getSNS().getMessage();
    logger.info("SNS Event message body is {}", message);

    JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();

    JsonObject responsePayload = jsonObject.getAsJsonObject("responsePayload");
    logger.info("responsePayload is {}", responsePayload.toString());
    
    int statusCode = responsePayload.get("statusCode").getAsInt();
    logger.info("statusCode is {}", statusCode);
    String word = responsePayload.get("word").getAsString();
    logger.info("word is {}", word);
    String translation  = responsePayload.get("translation").getAsString();
    logger.info("translation is {}", translation);
    
    return snsMessage.toString();
  }

  private void writeToDB(String word, Set<String> translations) {
    // TODO Auto-generated method stub
  }
}
