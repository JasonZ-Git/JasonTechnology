package org.jason.eureka.client.hello.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {

  @GetMapping("/users/status")
  public String checkStatus() {
    return "From Eureka Service client";
  }
}
