package org.jason.dictionary.translation.aws.model;

/**
 * Translation Result
 * 
 * @author Jason Zhang
 *
 */
public class TranslationResult {
  public static final int SUCCESS_CODE = 200;
  
  private int statusCode;
  private String word;
  private String translation;
  
  public boolean isSuccess() {
    return SUCCESS_CODE == statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getTranslation() {
    return translation;
  }

  public void setTranslation(String translation) {
    this.translation = translation;
  }
}
