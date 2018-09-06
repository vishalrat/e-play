package com.stackroute.eplay.upstreamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UpStreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpStreamServiceApplication.class, args);
	}
}
