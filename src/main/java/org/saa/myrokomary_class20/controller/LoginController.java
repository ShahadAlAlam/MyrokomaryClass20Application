package org.saa.myrokomary_class20.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.saa.myrokomary_class20.config.security.jwt.JwtTokenResponse;
import org.saa.myrokomary_class20.config.security.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class LoginController {
//    @GetMapping(value="/basicauth")
//    public String basicAuthCheck(){
//
//        return "hello world";
//    }
    private final JwtTokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest request;
    public LoginController(JwtTokenService tokenService,
                                       AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {
        System.out.println("login controller "+request.getRequestURI());

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(),
                        jwtTokenRequest.password());

        var authentication =
                authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generateToken(authentication);
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } else {
            return null;
        }
//        return ResponseEntity.ok(new JwtTokenResponse(token));

    }
    public record JwtTokenRequest(String username, String password) {}
}
