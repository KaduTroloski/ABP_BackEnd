package com.cinegraudos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/*").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/usuarios/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/sessoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/sessoes/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/sessoes/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/sessoes/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/reservar").authenticated()
                        .requestMatchers(HttpMethod.POST, "/reservar/*").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
