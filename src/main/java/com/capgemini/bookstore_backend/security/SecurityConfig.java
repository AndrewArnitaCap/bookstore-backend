package com.capgemini.bookstore_backend.security;

import com.capgemini.bookstore_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Implementing security configurations to secure my app, back-end, and API endpoints
 * Configuration: used for setting up application-level configuration
 * EnableWebSecurity: is part of Spring Security, to enable web security functionalities (HTTP sec, authentication, authorization...)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userDetailsService;

    /**
     * Dependency Injection using constructor injection
     */

    @Autowired
    public SecurityConfig(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Inspired from this article: https://medium.com/@tuananhbk1996/cors-with-spring-security-6-6b765f23ca5f
     * This method defines a CORS configuration to handle cross-origin requests
     * this will help me do api calls from the front-end
     * where it creates a `CorsConfiguration` object to specify the allowed origins and methods
     * UrlBasedCorsConfigurationSource` to register this configuration for the endpoints
     * @return CorsConfigurationSource hat Spring Security will use to manage cross-origin requests
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * sequence of filters that Spring Security applies to incoming HTTP requests
     * some of the filters are:
     * Authentication to handle user authentication
     * Authorization: to handle access controls and checks whether a user is authorized to access a resource
     * CSRF: to protects against CSRF attacks (not gonna included to keep my the security config minimal)
     * @param http
     * @return http.builder() which bundles up the object (chains)
     * @throws Exception
     */

    @Bean // an object that is managed by Spring and controls its lifecycle
    // HttpSecurity allows configuring web based security for http requests
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // used to fix the H2-console not loading in the browser
                .cors(Customizer.withDefaults()) // let spring security use the CORS configuration implemented above
                .csrf(AbstractHttpConfigurer::disable) // disabling cross site request forgery, to keep the security config simple
                .authorizeHttpRequests(request -> { // "The pattern must not contain the context path" so no need to add the base route /api
                    request.requestMatchers("/register/**").permitAll(); // give access to the /register directory and what comes after it "/**" to register a new user
                    request.anyRequest().authenticated(); // any other request will need to be authenticated to access any other directory
                })
                .formLogin(Customizer.withDefaults()) // the default login screen provided by Spring Security
                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic Authentication for API requests, allows me to send Authorization: Basic token in Postman.
                .build(); // finalize the object after the above configurations before returning it
    }

    /**
     * Inspired by: https://www.kindsonthegenius.com/how-to-authenticate-from-react-to-spring-boot/
     * and https://www.youtube.com/watch?v=h9LJd09tOks
     * In a way, we need to tell the UserService or the AuthProvider what type of password encoder we are using
     * and what type of authentication we are implementing
     * to do so we use this the AuthenticationProvider function below
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // DAO: Data access object, is conceptually similar to a Repository
        // this will load users from the DB and use it for authentication
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    /**
     * Implementation of PasswordEncoder that uses the BCrypt strong hashing function
     * @return the hashed password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
