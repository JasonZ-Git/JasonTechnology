package org.jason.dictionary.translation.aws.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SQSUtil {
  public static final String NEW_WORD_QUEUE =
      "https://sqs.ap-southeast-2.amazonaws.com/052752173794/NewWordQueue";

  public static void sendMessageToQueue(String word, String queue) {
    AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();

    CreateQueueRequest createQueueRequest = new CreateQueueRequest(queue);
    String myQueueURL = sqs.createQueue(createQueueRequest).getQueueUrl();

    sqs.sendMessage(new SendMessageRequest().withQueueUrl(myQueueURL).withMessageBody(word));
  }
}
