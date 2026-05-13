package com.quantity_measurement.quantity_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    // Extract username/email from token
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration
    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    // Generic claim extractor
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean isTokenValid(String token) {

        return !isTokenExpired(token);
    }

    // Check expiration
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Generate signing key
    private Key getSignKey() {

        byte[] keyBytes = secretKey.getBytes();

        return Keys.hmacShaKeyFor(keyBytes);
    }
}