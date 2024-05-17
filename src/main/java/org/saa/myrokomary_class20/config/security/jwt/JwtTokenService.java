package org.saa.myrokomary_class20.config.security.jwt;


import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import org.saa.myrokomary_class20.config.security.encriptions.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {

//    private String SECRET;
//
//    public JwtTokenService(){
//        SECRET = generateSecretKey();
//    }
//
//    public String generateSecretKey() {
//        try {
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secretKey = keyGen.generateKey();
//            System.out.println("Secret Key : " + secretKey.toString());
//            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error generating secret key", e);
//        }
//    }
//
//    public String getJwtToken(String username) {
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        claims.put("role", "USER");
//
//
//        return Jwts.builder().addClaims(claims).setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    private Key getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private HttpServletRequest request;

    public String generateToken(Authentication authentication) {
//        HashMap<String,Object> scope
        var scope
                = authentication
                .getAuthorities()
                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
                .collect(Collectors.toMap(GrantedAuthority::getAuthority,GrantedAuthority::getAuthority));
        HashMap<String,Object> requestData = new HashMap<>();
        requestData.put("remoteAddr",request.getRemoteAddr());
        requestData.put("remoteHost",request.getRemoteHost());
        requestData.put("requestedSessionId",request.getRequestedSessionId());
        requestData.put("referer",request.getHeader("Referer"));
        requestData.put("userAgent",request.getHeader("User-Agent"));
        requestData.put("Origin",request.getHeader("Origin"));
        Map<String,Object> dataScope = new HashMap<>();
        dataScope.put("scope",scope);
//        try {
//
//            byte[] someData = encryptionService.encryptHashMap(requestData);
//            dataScope.put("requestData", someData);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        scope.put("remoteAddr",request.getRemoteAddr());
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("dataScope", dataScope)
                .build();

        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    public String extractUserName(String token) {
        Jwt jwtEncoderParameters = this.jwtDecoder.decode(token);

        var claims = jwtEncoderParameters.getClaims();
        // extract the username from jwt token
        return claims.get("sub").toString();
    }

    public HashMap<String,Object> extractScope(String token) {
        Jwt jwtEncoderParameters = this.jwtDecoder.decode(token);

        var claims = jwtEncoderParameters.getClaims();
        // extract the username from jwt token
        return (HashMap<String,Object>)claims.get("dataScope");
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()));
    }


}
