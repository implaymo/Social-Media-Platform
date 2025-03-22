package com.SocialMediaPlatform.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure Spring Boot to serve static files
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/") // Path to your static resources
                .setCachePeriod(3600)  // Cache settings
                .resourceChain(true);  // Enable resource chain (for optimization)
    }
}
