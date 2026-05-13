package com.example.quantity_measurement_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Secret key used to sign and verify JWT tokens
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Token expiration time in milliseconds
    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;

    /**
     * Converts the secret key string into a secure Key object
     * used for signing JWT tokens.
     */
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Generates JWT token for authenticated user.
     */
    public String generateToken(UserDetails userDetails) {

        // Additional data can be stored inside claims if needed
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // Stores username/email
                .setIssuedAt(new Date()) // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // Expiry time
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signing algorithm
                .compact();
    }

    /**
     * Extracts username from JWT token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validates token by checking:
     * 1. Username matches
     * 2. Token is not expired
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks whether token is expired or not.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts expiration date from token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Generic method to extract any claim from token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from JWT token
     * after verifying token signature.
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // Verifies token using secret key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}