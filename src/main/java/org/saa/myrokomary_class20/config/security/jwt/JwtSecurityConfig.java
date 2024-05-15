package org.saa.myrokomary_class20.config.security.jwt;

import org.saa.myrokomary_class20.config.Configs;
import org.saa.myrokomary_class20.config.security.basic.CustomEncodersDecoders;
import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityConfig {
    @Autowired
    private AccountService userDetailsService;

    @Autowired
    private CustomEncodersDecoders customEncodersDecoders;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/saa/**").permitAll()
//                        .requestMatchers("/swagger-ui/index.html").permitAll()
//                        .requestMatchers("/api-docs/swagger-config").permitAll()
//                        .requestMatchers(PathRequest.toStaticResources()
//                                            .atCommonLocations()).permitAll()
//                        .requestMatchers(Configs.getAuthWhitelist()).permitAll()
//                        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/templates/**", "/static/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                    )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(
                        Customizer.withDefaults())
                .headers(header -> {
                    header.frameOptions().sameOrigin();
                })
                .build();
    }

    @Bean
    public AuthenticationManager myAuthenticationProvider(){
        DaoAuthenticationProvider myAuthenticationProvider = new DaoAuthenticationProvider();
        myAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        myAuthenticationProvider.setPasswordEncoder(this.customEncodersDecoders.passwordEncoder());

        return new ProviderManager( myAuthenticationProvider);
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                        .requestMatchers("/api/v1/auth/**",
                                "/v3/api-docs.yaml",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/fonts/**",
                                "/templates/**",
                                "/static/**");
    }

}
