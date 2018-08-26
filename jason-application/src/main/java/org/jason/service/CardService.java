package org.jason.service;

import java.util.List;

import org.jason.dao.Card;
import org.jason.dao.Greeting;

public interface CardService {

  List<Card> getAllCards();

  Greeting getMessage();
}
