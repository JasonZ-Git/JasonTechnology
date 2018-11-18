package org.jason.renting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.jason.renting"})
public class RentingApplication {

  public static void main(String[] args) {
    SpringApplication.run(RentingApplication.class, args);
  }
}
