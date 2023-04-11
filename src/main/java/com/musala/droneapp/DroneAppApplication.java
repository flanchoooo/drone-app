package com.musala.droneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@SpringBootApplication
@Component
@EnableScheduling
public class DroneAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneAppApplication.class, args);
	}

}
