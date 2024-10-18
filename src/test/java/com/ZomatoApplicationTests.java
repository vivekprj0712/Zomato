package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class ZomatoApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApplication.class, args);
	}
	

		
}
