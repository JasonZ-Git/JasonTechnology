package org.jason.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * If this application is intended to run as a normal java jar application, this class is enough. If
 * this application is intended to run as a war application under standard tomcat, then Use
 * WarApplication.
 * 
 * @author jason.zhang
 */

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.jason")
public class JarApplication {

  public static void main(String[] args) {
    SpringApplication.run(JarApplication.class, args);
  }
}
