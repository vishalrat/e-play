package com.stackroute.eplay.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TicketServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
		System.out.println("Hi ");
	}
}
