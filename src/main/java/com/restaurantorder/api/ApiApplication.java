package com.restaurantorder.api;

import com.restaurantorder.com.restaurantorder.util.RestaurantOrderUtil;
import com.restaurantorder.service.RestaurantOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.restaurantorder")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
