package com.AvailHive1.AvailHive1.configs;

import com.AvailHive1.AvailHive1.services.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter requestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                // Start authorization configurations.
                .authorizeHttpRequests()
                // Specify URL patterns that should be allowed without authentication.
                .requestMatchers("/authenticate", "/company/sign-up", "/client/sign-up", "/ads", "/search/{service}").permitAll()
                // All other requests need to be authenticated
                .and()
                .authorizeHttpRequests().requestMatchers("/api/**")
                .authenticated().and()
                // Configure session management to be stateless.
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Add your custom JWT request filter before the Spring Security filter.
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                // Build and return the SecurityFilterChain object.
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //using this bean to validate user password
}