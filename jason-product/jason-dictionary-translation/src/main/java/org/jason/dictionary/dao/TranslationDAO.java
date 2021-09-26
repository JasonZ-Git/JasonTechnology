package org.jason.dictionary.dao;

import java.util.List;
import org.jason.dictionary.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationDAO extends JpaRepository<Translation, Long> {
  List<Translation> findByWord(String word);
}
