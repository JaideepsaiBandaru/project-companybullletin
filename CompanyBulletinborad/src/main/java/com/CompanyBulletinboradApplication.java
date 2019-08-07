package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class CompanyBulletinboradApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyBulletinboradApplication.class, args);
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/company/signin").allowedOrigins("*");
                registry.addMapping("/company/signin").allowCredentials(true);
                
                registry.addMapping("/company/signup").allowedOrigins("*");
                registry.addMapping("/company/signup").allowCredentials(true);
                
                registry.addMapping("/company/signout").allowedOrigins("*");
                registry.addMapping("/company/signout").allowCredentials(true);
                
                registry.addMapping("/info/*").allowedOrigins("*");
                registry.addMapping("/info/*").allowCredentials(true);
            }
        };
}
}


