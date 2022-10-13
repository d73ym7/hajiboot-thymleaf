package com.example;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigureAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/loginForm")
                .defaultSuccessUrl("/customers", true)
                .failureUrl("/loginForm?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/loginForm")
        ).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .mvcMatchers("/LoginForm").permitAll()
                .mvcMatchers("/general").hasRole("GENERAL")
                .mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }


//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurationAdapter {
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/webjars/**,/css/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/loginForm").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .loginPage("/loginForm")
//                .failureUrl("/loginForm?error")
//                .defaultSuccessUrl("/customers", true)
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/loginForm");
//    }
}
