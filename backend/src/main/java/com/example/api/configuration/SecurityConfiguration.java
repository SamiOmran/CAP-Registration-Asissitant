//package com.example.api.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers("/H2-console/**").hasRole("ADMIN")
////             .and()
////                .formLogin()
////             .and()
////                .csrf()
////                .disable()
////                .headers()
////                .frameOptions()
////                .disable()
////             .and()
////                .httpBasic();
////    }
//
//
//}
