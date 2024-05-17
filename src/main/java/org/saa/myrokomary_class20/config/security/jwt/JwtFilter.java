package org.saa.myrokomary_class20.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.saa.myrokomary_class20.config.Configs;
import org.saa.myrokomary_class20.config.security.encriptions.EncryptionService;
import org.saa.myrokomary_class20.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    AccountService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        request.authenticate(response);
//        request.getRequestURI().
        String authHeader =  request.getHeader("authorization");
        String token = null;
        String userName = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")  && authHeader.length()>20){
            token = authHeader.substring(7);
            userName = jwtTokenService.extractUserName(token);
//            HashMap<String,Object> dataScope = jwtTokenService.extractScope(token);
//            HashMap<String,String> data = new HashMap<>();
//            try {
//                data = encryptionService.decryptHashMap((byte[])dataScope.get("requestData"));
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            System.out.println(data);
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);

            if(jwtTokenService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(request);
                authToken.setDetails(details);

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request)
//            throws ServletException {
//        String path = request.getRequestURI();
//        return true; // Configs.getAuthWhitelist().equals(path);
//    }
}
