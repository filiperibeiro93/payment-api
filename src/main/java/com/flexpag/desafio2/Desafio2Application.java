package com.flexpag.desafio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableCaching
@EnableWebSecurity
public class Desafio2Application {

	public static void main(String[] args) {
		SpringApplication.run(Desafio2Application.class, args);
	}

}
