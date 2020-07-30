package org.jason.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TranslationApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TranslationApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "10001"));
        app.run(args);
    }
}
