package org.jason.dictionary.service;

import java.util.List;
import org.jason.dictionary.dao.WordDAO;
import org.jason.dictionary.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
  @Autowired
  private WordDAO wordDAO;
  
  public List<Word> list(){
    return wordDAO.findAll();
  }
}
