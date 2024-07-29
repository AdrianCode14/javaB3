package com.spring.henallux.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.spring.henallux.ecommerce")
public class FirstSpringProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringProjectApplication.class, args);
	}

}
