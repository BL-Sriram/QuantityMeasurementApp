package com.quantity_measurement.auth_service.user.service;

import com.quantity_measurement.auth_service.security.JwtService;
import com.quantity_measurement.auth_service.user.dto.AuthResponse;
import com.quantity_measurement.auth_service.user.dto.LoginRequest;
import com.quantity_measurement.auth_service.user.dto.RegisterRequest;
import com.quantity_measurement.auth_service.user.entity.User;
import com.quantity_measurement.auth_service.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    // Repository used for database operations
    private final UserRepository userRepository;

    // Used for encrypting passwords
    private final PasswordEncoder passwordEncoder;

    // JWT utility service
    private final JwtService jwtService;

    // Handles authentication process
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user.
     */
    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole("ROLE_USER");

        user.setProvider("LOCAL");

        userRepository.save(user);

        return "User registered successfully";
    }

    /**
     * Authenticates user and generates JWT token.
     */
    public AuthResponse login(LoginRequest request) {

        // Authenticate email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch user from database
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        /*
         * Create UserDetails object required
         * for JWT token generation
         */
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole())
                        .build();

        // Generate JWT token
        String token = jwtService.generateToken(userDetails);

        // Return token inside response DTO
        return new AuthResponse(token);
    }
}