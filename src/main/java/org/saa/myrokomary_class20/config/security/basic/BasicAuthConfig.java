package org.saa.myrokomary_class20.config.security.basic;

import org.saa.myrokomary_class20.entity.AccountEntity;
import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class BasicAuthConfig {
    // 1. Spring Security Filter Chain to Filter which request will pass
    //  a. it will authenticate all request
    //      i. basic authentication
    //      ii. disable csrf (cross site request forgery)
    //      iii. no session will be stored (Stateless rest api)
    private static final String[] AUTH_WHITELIST = {
//            "/api/v1/auth/**",
//            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//      1. Request Response does not pass pre-flight request
//      2. Basic Auth
//        single chain of commands for basic authentication stateless csrf disable with request type and authentication in detail step by step

        return httpSecurity.authorizeHttpRequests( auth ->
                        auth
                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers(AUTH_WHITELIST).permitAll()
                            .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf().disable()
                .csrf(csrf -> csrf.disable())
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
