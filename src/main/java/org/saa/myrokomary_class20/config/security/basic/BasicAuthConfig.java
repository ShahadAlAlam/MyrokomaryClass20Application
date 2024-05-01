package org.saa.myrokomary_class20.config.security.basic;

import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthConfig {
    private final AccountService userDetailsService;

    BasicAuthConfig(AccountService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 1. Spring Security Filter Chain to Filter which request will pass
    //  a. it will authenticate all request
    //      i. basic authentication
    //      ii. disable csrf (cross site request forgery)
    //      iii. no session will be stored (Stateless rest api)
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//      1. Request Response does not pass pre-flight request
//      2. Basic Auth
//        single chain of commands for basic authentication stateless csrf disable with request type and authentication in detail step by step

        return httpSecurity.authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.OPTIONS, "/books/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/account-address/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/account/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/order/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/account/add-account").permitAll()
//                            .requestMatchers(
//                                    PathRequest
//                                            .toStaticResources()
//                                            .atCommonLocations()).permitAll()
                                .requestMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll() //permitAll()
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**", "/static/**").permitAll() //.permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
//                .csrf().disable()
                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .httpBasic(Customizer("/login").withDefaults())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(AbstractHttpConfigurer::disable)
                .build();

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
//        return httpSecurity.build();
    }

}
