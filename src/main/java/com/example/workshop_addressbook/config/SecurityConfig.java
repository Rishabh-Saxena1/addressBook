package com.example.workshop_addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (Use caution if handling forms)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(antMatcher("/api/auth/register"), antMatcher("/api/auth/login")).permitAll()
                        .anyRequest().authenticated() // All other requests need authentication
                )
                .formLogin(login -> login  // Enables form-based login (required for auth)
                        .loginPage("/login")  // Specify a login page (optional)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()); // Enables logout

        return http.build();
    }
}
