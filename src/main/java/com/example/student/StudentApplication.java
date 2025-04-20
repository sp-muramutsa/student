package com.example.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();

		System.setProperty("CONNECTION_URI", dotenv.get("CONNECTION_URI"));
		System.setProperty("PORT_NUMBER", dotenv.get("PORT_NUMBER"));
		System.setProperty("ADDRESS_BASE_URL", dotenv.get("ADDRESS_BASE_URL"));

		SpringApplication.run(StudentApplication.class, args);
	}

}
