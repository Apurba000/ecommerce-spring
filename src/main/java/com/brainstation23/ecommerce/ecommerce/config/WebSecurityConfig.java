package com.brainstation23.ecommerce.ecommerce.config;


import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("user/create").permitAll()
                        .requestMatchers("user/save").permitAll()
                        .requestMatchers("user/sign-in-form").permitAll()
                        .requestMatchers("user/sign-in").permitAll()
                        .requestMatchers("/user/cart/").hasAnyAuthority(String.valueOf(ERole.CUSTOMER))
                        .requestMatchers("/admin/**").hasAnyAuthority(String.valueOf(ERole.ADMIN))
                        .requestMatchers("/profile/**").authenticated()
                        .anyRequest().authenticated()
                )
                *//*.formLogin(login->
                        login.loginPage("/login")
                )
                .logout(logout->logout.permitAll())*//*
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userService)
                .build();*/
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userService)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
