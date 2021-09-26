package org.jason.dictionary.test;

import java.util.List;
import org.jason.dictionary.model.Word;
import org.jason.dictionary.service.DictionaryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryTest {

    @Autowired
    private DictionaryService service;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Word> books = service.list();

        Assert.assertEquals(books.size(), 1);
    }
}
