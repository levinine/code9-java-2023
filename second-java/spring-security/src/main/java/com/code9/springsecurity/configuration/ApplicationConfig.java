package com.code9.springsecurity.configuration;

import com.code9.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class provides the configuration for the Spring Security application.
 */
@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Returns a custom implementation of the UserDetailsService interface, which is
     * used to retrieve user details from the database based on the username.
     *
     * This implementation uses the UserRepository to find the user by their email address.
     *
     * @return a UserDetailsService instance that retrieves user details from the UserRepository
     * @throws UsernameNotFoundException if the user cannot be found in the UserRepository
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }


    /**
     * Returns a DaoAuthenticationProvider instance that is responsible for validating user
     * credentials during authentication. This method sets the UserDetailsService and
     * PasswordEncoder used for authentication.
     *
     * @return a DaoAuthenticationProvider instance that validates user credentials
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Returns an AuthenticationManager instance that is responsible for managing
     * the authentication process. This method uses the AuthenticationConfiguration to
     * retrieve the configured AuthenticationManager.
     *
     * @param authenticationConfiguration the AuthenticationConfiguration instance
     *                                     used to retrieve the configured AuthenticationManager
     * @return an AuthenticationManager} instance that manages the authentication process
     * @throws Exception if there is an error retrieving the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Returns a PasswordEncoder instance that is responsible for encoding user
     * passwords before they are stored in the database. This implementation uses the
     * BCryptPasswordEncoder algorithm to hash the password.
     *
     * @return a PasswordEncoder instance that encodes user passwords using the BCrypt algorithm
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
