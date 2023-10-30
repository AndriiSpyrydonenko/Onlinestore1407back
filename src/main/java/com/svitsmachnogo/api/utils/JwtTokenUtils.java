package com.svitsmachnogo.api.utils;

import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return buildJwt(userDetails.getUsername(), claims, issuedDate, expiredDate);
    }

    public String generateConfirmLink(RegistrationUserDTO userDTO){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", userDTO.getName());
        claims.put("password", userDTO.getPassword());
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return buildJwt(userDTO.getEmail(), claims, issuedDate, expiredDate);
    }

    public String getUserEmail(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getPassword(String token){
        return getAllClaimsFromToken(token).get("password",String.class);
    }

    public String getName(String token){
        return getAllClaimsFromToken(token).get("name",String.class);
    }

    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles",List.class);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private String buildJwt(String subject, Map<String, Object> claims, Date issuedDate, Date expiredDate) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(getSigningKey())
                .compact();
    }
}
