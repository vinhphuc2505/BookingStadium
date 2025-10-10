package com.BookingStadium.StadiumService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class StadiumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StadiumServiceApplication.class, args);
	}

}
