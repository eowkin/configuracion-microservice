package com.usb.ve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
//@EnableFeignClients
public class CustomerServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceClientApplication.class, args);
	}

}
