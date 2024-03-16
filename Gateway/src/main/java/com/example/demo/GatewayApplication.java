package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableDiscoveryClient
@SpringBootApplication

public class GatewayApplication {
	
	  @Bean
	  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	  return builder.routes()
	    .route("inventory", r -> r.path("/inventory/**")
	      .filters(f -> f.stripPrefix(1))
	      .uri("lb://inventory-service"))
	    .route("product", r -> r.path("/product/**")
	      .filters(f -> f.stripPrefix(1))
	      .uri("http://localhost:8085"))
	    .route("cart", r -> r.path("/cart/**")
	  	      .filters(f -> f.stripPrefix(1))
	  	      .uri("http://localhost:9080"))
	    .build();
	  }

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	

	}


