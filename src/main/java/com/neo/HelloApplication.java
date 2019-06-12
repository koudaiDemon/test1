package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan()
public class HelloApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext run = SpringApplication.run(HelloApplication.class, args);
	}
}
