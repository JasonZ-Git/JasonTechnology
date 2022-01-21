package org.jason.dictionary.translation.aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.dictionary.translation.aws.model.DictionaryItem;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

/**
 * Init Dictionary table - Read from S3 bucket
 *
 * <p>Only used to init - when the DynamoDB table is empty
 *
 * @author Jason Zhang
 */
public class OnlyUsedOnInit_InitTranslationHandler
    implements RequestHandler<APIGatewayV2HTTPEvent, String> {

  private static final Logger logger =
      LogManager.getLogger(OnlyUsedOnInit_InitTranslationHandler.class);
  private static final int MAX_BATCH_SIZE = 25;
  private static final String BUCKET_NAME = "jason-dictionary";
  private static final String DICTIONARY_FILE_NAME = "final-dictionary.properties";
  private static final String DYNAMODB_TABLE_NAME = "dictionary";

  @Override
  public String handleRequest(APIGatewayV2HTTPEvent input, Context context) {

    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);

    if (!isEmpty(client, DYNAMODB_TABLE_NAME)) {
      return "Not Init as it is not empty";
    }

    List<DictionaryItem> items = readAllItems();
    writeInBatch(mapper, items);

    return String.format("Success initilised %d items", items.size());
  }

  private void writeInBatch(DynamoDBMapper mapper, List<DictionaryItem> items) {
    List<DictionaryItem> oneTimeItems = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      if (i % MAX_BATCH_SIZE == MAX_BATCH_SIZE - 1 || i == items.size() - 1) {
        mapper.batchSave(items);
        oneTimeItems.clear();
        break;
      }
      oneTimeItems.add(items.get(i));
    }
  }

  private Boolean isEmpty(AmazonDynamoDB database, String tableName) {
    ScanRequest scanRequest = new ScanRequest().withTableName(tableName).withLimit(1);
    return database.scan(scanRequest).getCount() == 0;
  }

  private List<DictionaryItem> readAllItems() {
    List<DictionaryItem> dictioanary = new ArrayList<DictionaryItem>();
    try {
      AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();
      S3Object s3Object = s3.getObject(BUCKET_NAME, DICTIONARY_FILE_NAME);
      BufferedReader in = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));
      String line = null;
      List<String> linesS3 = new ArrayList<>();
      while ((line = in.readLine()) != null) {
        linesS3.add(line);
      }

      for (String current : linesS3) {
        DictionaryItem item = new DictionaryItem();
        if (current == null || !current.contains("=")) {
          continue;
        }
        String[] wordTrans = current.split("=");
        item.setWord(wordTrans[0]);

        if (wordTrans[1] == null && wordTrans[1].isBlank()) continue;

        String[] translations = wordTrans[1].split(",");
        item.addAllTranslations(Arrays.asList(translations));

        dictioanary.add(item);
      }

    } catch (IOException e) {
      logger.error("Error reading items from S3", e);
    }

    return dictioanary;
  }
}
