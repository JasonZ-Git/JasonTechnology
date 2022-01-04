package org.jason.dictionary.translation.aws;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.model.DictionaryItem;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

public class ReadTranslationHandler implements RequestHandler<APIGatewayV2HTTPEvent, String> {

  private static final Logger logger = LogManager.getLogger(ReadTranslationHandler.class);

  @Override
  public String handleRequest(APIGatewayV2HTTPEvent input, Context context) {

    String word = input.getRawQueryString();
    if (StringUtils.isBlank(word)) {
      String msg = "Query param is empty";
      logger.info(msg);
      return msg;
    }

    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);

    DictionaryItem item = new DictionaryItem();
    item.setWord(word);

    DynamoDBQueryExpression<DictionaryItem> queryExpression =
        new DynamoDBQueryExpression<DictionaryItem>().withHashKeyValues(item);

    List<DictionaryItem> itemList = mapper.query(DictionaryItem.class, queryExpression);

    if (itemList.isEmpty()) {
      String msg = String.format("No translation Found for %s", word);
      logger.info(msg);
      return msg;
    }

    return itemList.stream().map(item1 -> item1.toString()).collect(Collectors.joining("/n"));
  }
}
