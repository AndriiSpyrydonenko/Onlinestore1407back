package com.svitsmachnogo.api.utils;

import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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

/**
 * Utility class for generating and processing JSON Web Tokens (JWTs).
 */
@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    @Value("${jwt.confirm.lifetime}")
    private Duration jwtConfirmLifetime;

    /**
     * Generates a JWT token for the provided UserDetails object, typically representing
     * user authentication information.
     *
     * @param userDetails The UserDetails object containing user authentication details.
     * @return A JWT token as a String.
     * @author Vanya Demydenko
     */
    public String generateToken(UserDetails userDetails) {
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

    /**
     * Generates a JWT token for confirming user registration.
     *
     * @param userDTO The RegistrationUserDTO object with registration information.
     * @return A JWT token for confirmation as a String.
     * @author Vanya Demydenko
     */
    public String generateConfirmKey(RegistrationUserDTO userDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", userDTO.getName());
        claims.put("password", userDTO.getPassword());
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtConfirmLifetime.toMillis());

        return buildJwt(userDTO.getEmail(), claims, issuedDate, expiredDate);
    }

    /**
     * Parses a user registration DTO from a JWT.
     *
     * @param token The JWT string from which the user information will be extracted.
     * @return A RegistrationUserDTO object containing user information parsed from the JWT.
     * @throws JwtException If there is an issue parsing the JWT or extracting claims.
     * @see #getAllClaimsFromToken(String)
     */
    public RegistrationUserDTO parseUserFromJwt(String token) throws JwtException {
        RegistrationUserDTO userDTO = new RegistrationUserDTO();
        Claims claims = getAllClaimsFromToken(token);
        userDTO.setEmail(claims.getSubject());
        userDTO.setName(claims.get("name", String.class));
        userDTO.setPassword(claims.get("password", String.class));
        return userDTO;
    }

    /**
     * Retrieves the email address from a JWT token.
     *
     * @param token The JWT token from which to extract the email address.
     * @return The email address contained in the token.
     * @author Vanya Demydenko
     */
    public String getUserEmail(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Retrieves the password from a JWT token.
     *
     * @param token The JWT token from which to extract the password.
     * @return The password contained in the token.
     * @author Vanya Demydenko
     */
    public String getPassword(String token) {
        return getAllClaimsFromToken(token).get("password", String.class);
    }

    /**
     * Retrieves the name from a JWT token.
     *
     * @param token The JWT token from which to extract the name.
     * @return The name contained in the token.
     * @author Vanya Demydenko
     */
    public String getName(String token) {
        return getAllClaimsFromToken(token).get("name", String.class);
    }

    /**
     * Retrieves a list of roles from a JWT token.
     *
     * @param token The JWT token from which to extract the list of roles.
     * @return A list of roles contained in the token.
     * @author Vanya Demydenko
     */
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    /**
     * Parses a JWT token, verifies its signature, and retrieves the claims contained within the token.
     *
     * @param token The JWT token to be parsed and verified.
     * @return A Claims object representing the claims contained within the JWT token.
     * @author Vanya Demydenko
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Generates a secret key for signing and verifying JWT tokens using the configured secret.
     *
     * @return A SecretKey instance used for JWT token signing.
     * @author Vanya Demydenko
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Builds a JWT token with the provided subject, claims, issued date, and expiration date.
     *
     * @param subject     The subject of the JWT token (e.g., username or email).
     * @param claims      The claims to include in the JWT token.
     * @param issuedDate  The date when the token is issued.
     * @param expiredDate The date when the token expires.
     * @return A JWT token as a String.
     * @author Vanya Demydenko
     */
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
