package com.example.Zapy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZapyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZapyApplication.class, args);
	}

}
