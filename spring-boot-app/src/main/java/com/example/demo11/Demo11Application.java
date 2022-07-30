package com.example.demo11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class Demo11Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo11Application.class, args);
	}

}
