package com.clone.crunchroll.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.clone.crunchroll.controller.AuthenticationSuccessHandler;
import com.clone.crunchroll.model.cliente.ClienteDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ClienteDetailService userDetailsService;

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
            
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/videos/**").permitAll();
                    registry.requestMatchers("/home", "/register/**", "/css/**", "/fragments/**", "/static/**","/js/**", "/img/**").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
                    
                })
                
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                            
                })
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") // Redireciona após logout
                .permitAll() // Permitir acesso ao logout
            )
                .build();
        

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}