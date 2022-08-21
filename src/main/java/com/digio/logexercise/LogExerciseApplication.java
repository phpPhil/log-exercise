package com.digio.logexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class LogExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogExerciseApplication.class, args);
	}
}
