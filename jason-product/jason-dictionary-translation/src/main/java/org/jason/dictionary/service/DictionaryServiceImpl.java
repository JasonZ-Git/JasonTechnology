package org.jason.dictionary.service;

import java.util.List;
import java.util.stream.Collectors;
import org.jason.dictionary.dao.TranslationDAO;
import org.jason.dictionary.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {
  
  @Autowired
  private TranslationDAO translationDAO;
  
  @Override
  public List<Translation> list(){
    return translationDAO.findAll();
  }

  @Override
  public List<String> getTranslations(String word) {
    List<Translation> translations = translationDAO.findByWord(word);
    return translations.stream().sorted((left, right)-> left.getSequence()
        .compareTo(right.getSequence()))
        .map(item -> item.getTranslation())
        .collect(Collectors.toList());
  }
}
