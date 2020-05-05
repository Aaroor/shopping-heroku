package com.shopping.heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingHerokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingHerokuApplication.class, args);
	}

}
