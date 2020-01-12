package org.jason.spider.yeeyi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.jason.renting"})
public class YeeyiWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(YeeyiWebApplication.class, args);
  }
}
