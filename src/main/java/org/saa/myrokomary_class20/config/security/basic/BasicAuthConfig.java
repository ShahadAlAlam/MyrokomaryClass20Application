package org.saa.myrokomary_class20.config.security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthConfig {
    // 1. Spring Security Filter Chain to Filter which request will pass
    //  a. it will authenticate all request
    //      i. basic authentication
    //      ii. disable csrf (cross site request forgery)
    //      iii. no session will be stored (Stateless rest api)

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        below line disables all secutiry for get request but for others it needs user name and password
//        return httpSecurity.build();

////        basic authentication stateless csrf disable with request type and authentication in detail step by step
////        to enable other security we need to pass any request throw authentication
//        httpSecurity.authorizeHttpRequests(
//                auth -> auth.anyRequest().authenticated()
//        );
////        to Access those request we have to enable basic authentication
//        httpSecurity.httpBasic(Customizer.withDefaults());
////        to make request stateless for disabling csrf
//        httpSecurity.sessionManagement(
//                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
////        to Disable csrt
//        httpSecurity.csrf().disable();

//        single chain of commands for basic authentication stateless csrf disable with request type and authentication in detail step by step
        httpSecurity.authorizeHttpRequests( auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable();
        return httpSecurity.build();
    }

}
