package com.bootcamp.bankproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class BankproductApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankproductApplication.class, args);
	}

}
