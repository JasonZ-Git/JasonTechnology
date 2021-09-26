package org.jason.dictionary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "dictionary_translation")
public class Translation {
  @Id
  @GeneratedValue
  private Long id;
  
  private String word;
  
  private String translation;
  
  private Integer sequence;
  
  private String word_language;
  
  private String translation_language;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getSequence() {
    return sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  public String getWord_language() {
    return word_language;
  }

  public void setWord_language(String word_language) {
    this.word_language = word_language;
  }

  public String getTranslation_language() {
    return translation_language;
  }

  public void setTranslation_language(String translation_language) {
    this.translation_language = translation_language;
  }
}
