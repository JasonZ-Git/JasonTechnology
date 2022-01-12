package org.jason.eureka.client.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientHelloApplication.class, args);
	}

}
