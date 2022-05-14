package com.example.instaclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InstaCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaCloneApplication.class, args);
	}

}
