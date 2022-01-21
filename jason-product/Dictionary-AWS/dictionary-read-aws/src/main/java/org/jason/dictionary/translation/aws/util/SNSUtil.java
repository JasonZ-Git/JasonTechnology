package org.jason.dictionary.translation.aws.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;

public class SNSUtil {
  public static final String NEW_WORD_SNS_TOPIC =
      "arn:aws:sns:ap-southeast-2:052752173794:NewWordTopic";

  public static void publishMessageToTopic(String word, String topic) {
    AmazonSNS sns = AmazonSNSClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_2).build();
    PublishRequest snsRequest =  new PublishRequest(NEW_WORD_SNS_TOPIC, word);
    sns.publish(snsRequest);
  }
}
