package org.jason.restful;

import java.util.List;

import org.jason.dao.Card;
import org.jason.dao.Greeting;
import org.jason.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

  @Autowired
  private CardService cardService;

  @RequestMapping(value = "/getAllCards", method = RequestMethod.GET)
  public List<Card> getAllCards() {
    return cardService.getAllCards();
  }

  @RequestMapping(value = "/cardMsg", method = RequestMethod.GET)
  public Greeting getCard(@RequestParam(name = "seconds", defaultValue = "60") Integer seconds) {
    Assert.isTrue(seconds > 0, "Seconds can't be negative");
      
    return cardService.getMessage();
  }

}
