package com.thomas.blog.config;

import com.thomas.blog.domain.entities.User;
import com.thomas.blog.repositories.UserRepository;
import com.thomas.blog.security.BlogUserDetailsService;
import com.thomas.blog.security.JwtAuthenticationFilter;
import com.thomas.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        BlogUserDetailsService blogUserDetailsService = new BlogUserDetailsService(userRepository);
        String email="user@test.com";
        userRepository.findByEmail(email).orElseGet(()->{
            User newUser=User.builder()
                    .name("Test User")
                    .email(email)
                    .password(passwordEncoder().encode("password123"))
                    .build();
           return userRepository.save(newUser);
        });

        return blogUserDetailsService;
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter(AuthenticationService authenticationService){
        return new JwtAuthenticationFilter(authenticationService);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter authenticationFilter) throws Exception{
        http

                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.POST,"/api/v1/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/tags/**").permitAll()
//                        .anyRequest().authenticated()
                        .anyRequest().permitAll())
//                .authenticationProvider(authenticationProvider(userDetailsService(userRepository), passwordEncoder()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement
                        (session->session.
                                sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                         ).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); // Inject via constructor
            provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
