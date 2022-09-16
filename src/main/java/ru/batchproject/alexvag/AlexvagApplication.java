package ru.batchproject.alexvag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableJpaRepositories
@SpringBootApplication
public class AlexvagApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexvagApplication.class, args);
	}

}
