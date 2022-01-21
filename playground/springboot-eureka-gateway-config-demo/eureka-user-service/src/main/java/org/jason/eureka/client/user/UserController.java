package org.jason.eureka.client.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  
  @GetMapping("/users")
  public String getUsers() {
    return "All Users";
  }
  
  @GetMapping("/users/{user}")
  public String getBooksByName(@PathVariable String user) {
    return "The first User";
  }
}
