package org.jason.dictionary.translation.aws;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.model.DictionaryItem;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import com.amazonaws.util.CollectionUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Write new translation to DB
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

    if (statusCode != HttpStatus.SC_OK) {
      throw new RuntimeException("Status code is " + statusCode);
    }

    String word = responsePayload.get("word").getAsString();
    logger.info("word is {}", word);
    String translation = responsePayload.get("translation").getAsString();
    logger.info("translation is {}", translation);

    writeToDB(word, translation);

    return snsMessage.toString();
  }

  private void writeToDB(String word, String translations) {
    
    if (StringUtils.isBlank(word) || StringUtils.isBlank(translations)) {
      logger.warn("Word or translation is empty");
      
      return;
    }
    
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);

    DictionaryItem item = new DictionaryItem();
    item.setWord(word);

    DynamoDBQueryExpression<DictionaryItem> queryExpression =
        new DynamoDBQueryExpression<DictionaryItem>().withHashKeyValues(item);

    List<DictionaryItem> itemList = mapper.query(DictionaryItem.class, queryExpression);
    if (!CollectionUtils.isNullOrEmpty(itemList)) {
      logger.warn("translation already exists, so not create new item");
      return;
    }

    Set<String> translationSet = new HashSet<String>(Arrays.asList(translations.split(",")));

    item.setTranslation(translationSet);
    mapper.save(item);
    
    logger.info("new word {} and translation {} save to DB ", word, translations);
  }
}
