package com.erico.lavanderia.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig {

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        var cors = new CorsConfiguration();
//        cors.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200"));
//        cors.setAllowedMethods(List.of("*"));
//        cors.setAllowedHeaders(List.of("*"));
//        cors.setAllowCredentials(true);
//
//        var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", cors);
//        return source;
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "http://127.0.0.1:4200")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
