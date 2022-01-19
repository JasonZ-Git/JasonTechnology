package org.jason.eureka.client.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaClientUserApplication.class, args);
  }
}
