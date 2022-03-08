package com.niit.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
public class APIConfiguration {
    @Bean
    public RouteLocator myRoutes (RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
                .routes()
                .route(p -> p
                        .path("/api/customer/**")
                        .uri("lb://customer-service"))
                .route(p -> p
                        .path("/api/restaurant/**")
                        .uri("lb://restaurant-service"))
                .route(p -> p
                        .path("/api/favourite/**")
                        .uri("lb://favourite-service"))
                .build();
    }

   }
