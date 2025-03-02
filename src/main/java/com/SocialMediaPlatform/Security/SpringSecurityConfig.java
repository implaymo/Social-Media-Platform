package com.SocialMediaPlatform.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for API endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()  // Allow registration without auth
                        .requestMatchers("/auth/login").permitAll()     // Allow login without auth
                        // Add other public endpoints if needed
                        .anyRequest().authenticated()  // All other endpoints need authentication
                );

        return http.build();
    }
}
