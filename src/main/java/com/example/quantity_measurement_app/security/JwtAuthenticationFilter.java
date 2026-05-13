package com.example.quantity_measurement_app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Service used for JWT operations like validation and extracting username
    private final JwtService jwtService;

    // Custom service to load user details from database
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Fetch Authorization header from request
        final String authHeader = request.getHeader("Authorization");

        final String jwt;
        final String userEmail;

        // Check if header is missing or token does not start with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            // Continue request without authentication
            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " from token
        jwt = authHeader.substring(7);

        // Extract username/email from JWT token
        userEmail = jwtService.extractUsername(jwt);

        // Authenticate only if user is not already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from database
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            // Validate token
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Create authentication token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // Attach request details like IP address and session info
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set authentication inside Security Context
                // so Spring Security knows user is authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue request processing
        filterChain.doFilter(request, response);
    }
}