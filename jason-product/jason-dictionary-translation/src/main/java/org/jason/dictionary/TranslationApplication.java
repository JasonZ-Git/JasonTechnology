package org.jason.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TranslationApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(TranslationApplication.class);

        setRunningParameters(args, app);

        app.run(args);
    }

    private static void setRunningParameters(String[] args, SpringApplication app) {

        if (args != null && args.length !=0 && args[0].equals("dev_mode")) {
            System.setProperty("mode", "dev");
            app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
        } else {
            System.setProperty("mode", "production");
            app.setDefaultProperties(Collections.singletonMap("server.port", "10001"));
        }
    }
}