package com.stackroute.eplay.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.eplay.userregistration.config.JwtFilter;

@SpringBootApplication
public class UserRegistrationApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/secure/*");

		return registrationBean;
	}

	// @Bean
	// public BCryptPasswordEncoder bCryptPasswordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}
}
