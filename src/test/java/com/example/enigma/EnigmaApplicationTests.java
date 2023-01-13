package com.example.enigma;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {EnigmaApplication.class})
@EnableConfigurationProperties(EnigmaConfig.class)
@TestPropertySource(locations = "classpath:application.yaml")
class EnigmaApplicationTests {

	@Autowired
	private EnigmaConfig enigmaConfig;

	@Autowired
	private EnigmaService enigmaService;

	@Test
	void contextLoads() {
		String data = "123:TEST:ABC";
		String encrypted = enigmaService.encrypt(data);
		String decrypted = enigmaService.decrypt(encrypted);

		System.out.println(enigmaService.getSymbols());
		System.out.println("V");
		System.out.println(enigmaConfig.getKey());
		System.out.println("V");
		System.out.println(data);
		System.out.println("V");
		System.out.println(encrypted);
		System.out.println("V");
		System.out.println(decrypted);
	}

}
