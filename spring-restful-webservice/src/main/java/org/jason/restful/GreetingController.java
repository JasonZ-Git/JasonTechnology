package org.jason.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  private static final String template = "Hello, %s";
  private final AtomicLong counter = new AtomicLong();
  
  @RequestMapping(value="/getGreeting", method=RequestMethod.GET)
  public Greeting getGreeting(@RequestParam(value = "name", defaultValue="world") String name){
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
}
