//package org.blueliner.gateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//
//@Configuration
//public class ProxyConfig {
//    @Bean
//    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("shop-service",
//                        route -> route.path("/api/**")
//                                .and()
//                                .method(HttpMethod.GET, HttpMethod.POST)
//                                .uri("lb://shop-service"))
//                .build();
//    }
//}
//
