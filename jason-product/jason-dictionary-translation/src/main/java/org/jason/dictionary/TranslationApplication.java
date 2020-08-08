package org.jason.dictionary;

import com.google.common.base.Strings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class TranslationApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TranslationApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "10001"));

        if (args != null && args.length !=0 && args[0].equals("dev_mode")) {
            System.setProperty("mode", "dev");
        } else {
            System.setProperty("mode", "production");
        }

        app.run(args);
    }
}
