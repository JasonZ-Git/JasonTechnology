package org.jason.spider.yeeyi.application;

import org.jason.annotation.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.jason.spider.yeeyi"})
@Application(name = "Yeeyi Web Application - Crawl yeeyi.com.au")
public class YeeyiWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(YeeyiWebApplication.class, args);
  }
}
