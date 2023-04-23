package com.code9.springsecurity.configuration;

import com.code9.springsecurity.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is responsible for configuring Spring Security for the application. It
 * enables web security and configures security filters and authentication providers.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthFilter;

    private AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Configures the security filter chain that is responsible for processing incoming
     * requests and authenticating users.
     *
     * @param http the HttpSecurity object used to configure the security filter chain
     * @return a SecurityFilterChain instance that processes incoming requests and authenticates users
     * @throws Exception if there is an error configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**", "/api/hello-everyone")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
