package com.example.food_delivery.configuration.security;

import com.example.food_delivery.dto.ErrorData;
import com.example.food_delivery.repository.AuthUserRepository;
import com.example.food_delivery.repository.CartRepository;
import com.example.food_delivery.service.AuthUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final ObjectMapper objectMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final CartRepository cartRepository;
    private final AuthUserRepository authUserRepository;

    public SecurityConfiguration(ObjectMapper objectMapper, JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService, CartRepository cartRepository, AuthUserRepository authUserRepository) {
        this.objectMapper = objectMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.cartRepository = cartRepository;
        this.authUserRepository = authUserRepository;
    }

    private final static String[] WHITE_LIST = {"/swagger-ui/**", "/api/auth/**", "/v3/api-docs/**", "/error", "/oauth2/**", "/login/oauth2/code/google"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(httpReqConfigurer -> httpReqConfigurer.requestMatchers(WHITE_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtTokenFilter(jwtTokenUtil, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler(jwtTokenUtil, authUserRepository, cartRepository))
                )
                .build();
    }
    @Bean
    public OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler(JwtTokenUtil jwtTokenUtil, AuthUserRepository authUserRepository, CartRepository cartRepository) {
        return new OAuth2LoginSuccessHandler(jwtTokenUtil, authUserRepository, cartRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            authException.printStackTrace();
            String errorPath = request.getRequestURI();
            String errorMessage = authException.getMessage();
            int errorCode = 401;
            ErrorData errorData = new ErrorData(errorMessage, errorPath);
            response.setStatus(errorCode);
            response.setContentType("application/json");
            ServletOutputStream outputStream = response.getOutputStream();
            objectMapper.writeValue(outputStream, errorData);
        };
    }


}
