package com.wisdombookshop;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.wisdombookshop.controllers.EmployeeController;
import com.wisdombookshop.entities.BookCategory;
import com.wisdombookshop.entities.User;
import com.wisdombookshop.services.BookCategoryService;
import com.wisdombookshop.services.UserService;

@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class WisdombookshopApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(WisdombookshopApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}

	

}
