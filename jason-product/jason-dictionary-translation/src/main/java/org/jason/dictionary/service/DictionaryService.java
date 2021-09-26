package org.jason.dictionary.service;

import java.util.List;
import org.jason.dictionary.model.Translation;

public interface DictionaryService {

  public List<Translation> list();
  
  public List<String> getTranslations(String word);
}
