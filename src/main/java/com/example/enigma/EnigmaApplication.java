package com.example.enigma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({EnigmaConfig.class})
public class EnigmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmaApplication.class, args);
	}

}
