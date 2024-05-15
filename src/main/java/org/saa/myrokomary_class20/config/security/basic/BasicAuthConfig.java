package org.saa.myrokomary_class20.config.security.basic;

import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class BasicAuthConfig {
    public AccountService getUserDetailsService() {
        return userDetailsService;
    }

    private final AccountService userDetailsService;
    private final CustomEncodersDecoders customEncodersDecoders;

    BasicAuthConfig(AccountService userDetailsService, CustomEncodersDecoders customEncodersDecoders) {
        this.userDetailsService = userDetailsService;
        this.customEncodersDecoders = customEncodersDecoders;
    }

    @Bean
    public AuthenticationProvider myAuthenticationProvider(){
        DaoAuthenticationProvider myAuthenticationProvider = new DaoAuthenticationProvider();
        myAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        myAuthenticationProvider.setPasswordEncoder(this.customEncodersDecoders.passwordEncoder());
        return myAuthenticationProvider;
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

    private static final String[] AUTH_METHODS ={
            "GET","POST",
            "PUT","DELETE",
            "OPTIONS","HEAD",
            "PATCH","TRACE"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//      1. Request Response does not pass pre-flight request
//      2. Basic Auth
//        single chain of commands for basic authentication stateless csrf disable with request type and authentication in detail step by step

        return httpSecurity.authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/swagger-ui/index.html").permitAll()
//                            .requestMatchers(
//                                    PathRequest
//                                            .toStaticResources()
//                                            .atCommonLocations()).permitAll()
                                .requestMatchers(AUTH_WHITELIST).permitAll() //permitAll()
                                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**", "/static/**").permitAll() //.permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .csrf().disable()
                .csrf(csrf -> csrf.disable())
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
