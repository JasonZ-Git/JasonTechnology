package org.jason.cloud.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

// @Configuration - Either use this coding configuration or use file configuration
public class SpringCloudConfig {
  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(r -> r.path("/users/**").uri("lb://User-Service"))
        .route(r -> r.path("/books/**").uri("lb://Book-Service"))
        .build();
  }
}
