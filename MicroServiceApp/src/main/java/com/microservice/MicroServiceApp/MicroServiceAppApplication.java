package com.microservice.MicroServiceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
//@EnableHystrixDashboard
public class MicroServiceAppApplication {
	
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceAppApplication.class, args);
		
	//	productService.listAll();
	}

}
