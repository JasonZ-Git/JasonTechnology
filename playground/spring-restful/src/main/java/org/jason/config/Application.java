package org.jason.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * If this application is intended to run as a normal java jar application, this class is enough.
 * If this application is intended to run as a war application under standard tomcat, then Use WarApplication.
 *
 * @author jason.zhang
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
