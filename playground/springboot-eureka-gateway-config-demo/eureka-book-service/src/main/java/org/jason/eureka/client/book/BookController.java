package org.jason.eureka.client.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  @GetMapping("/books")
  public String getBooks() {
    return "Books Services";
  }
  
  @GetMapping("/books/{name}")
  public String getBooksByName(@PathVariable String name) {
    return "The Lost Victory";
  }
}
