package ru.blaskowitz.userserviceadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class UserServiceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceAdminApplication.class, args);
	}

}
