package org.jason.eurekahello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaHelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaHelloApplication.class, args);
  }
}