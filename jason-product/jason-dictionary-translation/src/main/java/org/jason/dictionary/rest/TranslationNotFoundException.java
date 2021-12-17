package org.jason.dictionary.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TranslationNotFoundException extends RuntimeException {

  public TranslationNotFoundException(String message) {
    super(message);
  }
  
}
