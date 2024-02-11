package com.blog.blogingapp.Configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.blogingapp.Security.CustomUserDetailService;
import com.blog.blogingapp.Security.JwtAuthenticationEntryPoint;
import com.blog.blogingapp.Security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.csrf(csrf -> csrf.disable())
        //     .cors(cors->cors.disable())
        //     .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        //     .httpBasic(b->{});
        //     // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        // return http.build();
        http.csrf(csrf -> csrf.disable())
            .cors(cors->cors.disable())
            // .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated().requestMatchers("/api/v1/auth/login").permitAll().anyRequest().authenticated())
            .authorizeHttpRequests(auth->auth.requestMatchers("/api/v1/auth/login").permitAll().anyRequest().authenticated())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder)
            throws Exception {
        return builder.getAuthenticationManager();
    }

    
    public UserDetailsService userDetailService(){
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}

/*
 * 
1 --- Responsibility Transfer:

    The AuthenticationManager is responsible for orchestrating the authentication process, but it doesn't directly perform the authentication logic.


2 --- Passing the Task:

    When an authentication request is received, the AuthenticationManager identifies the appropriate AuthenticationProvider to handle the request based on the authentication mechanism.


3--- Handing off Control:

    The AuthenticationManager hands over the authentication request, along with the user's credentials, to the selected AuthenticationProvider.


4 --- Specialized Handling:

    Each AuthenticationProvider specializes in a specific authentication mechanism (e.g., in-memory authentication, database authentication, LDAP authentication).
    The AuthenticationProvider is responsible for executing the authentication logic tailored to its authentication mechanism.

5 --- Result Return:

    After processing the authentication request, the AuthenticationProvider returns the authentication result to the AuthenticationManager.


6 --- Aggregation of Results:

    If multiple AuthenticationProviders are configured, the AuthenticationManager aggregates the results from each provider and determines the overall authentication outcome.
*/

