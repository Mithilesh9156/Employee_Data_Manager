package com.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ManagementSystem1Application{


	public static void main(String[] args) {
		SpringApplication.run(ManagementSystem1Application.class, args);
	}

	@Bean
	CommandLineRunner runner(MainLogic logic) {
		
		return args -> { ((MainLogic) logic).run();
	
		};
	}

}
