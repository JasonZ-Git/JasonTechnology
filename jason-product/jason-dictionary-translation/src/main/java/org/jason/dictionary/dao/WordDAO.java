package org.jason.dictionary.dao;

import org.jason.dictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordDAO extends JpaRepository<Word, Long> {
  
}
