package org.jason.dictionary.translation.aws.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Dictionary")
public class DictionaryItem {
  private String word;
  private Set<String> translation = new HashSet<>();

  @DynamoDBHashKey(attributeName = "word")
  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  @DynamoDBAttribute(attributeName = "translation")
  public Set<String> getTranslation() {
    return translation;
  }

  public void setTranslation(Set<String> translation) {
    this.translation = translation;
  }

  public void addTranslation(String translation) {
    this.translation.add(translation);
  }

  public void addAllTranslations(List<String> translations) {
    this.translation.addAll(translations);
  }

  @Override
  public String toString() {
    if (this.translation.isEmpty()) {
      this.translation = new HashSet<String>(Arrays.asList("[]"));
    }

    String translationSentence = translation.stream().collect(Collectors.joining(","));

    return String.format("%s - %s", this.word, translationSentence);
  }
}
