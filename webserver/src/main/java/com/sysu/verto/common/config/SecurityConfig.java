package com.sysu.verto.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for simplicity
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll() // Allow all requests under /api/** without authentication
                .anyRequest().authenticated() // Other requests need authentication
                .and()
                .formLogin().disable() // Disable form login
                .httpBasic().disable(); // Disable HTTP Basic authentication
        return http.build();
    }

}