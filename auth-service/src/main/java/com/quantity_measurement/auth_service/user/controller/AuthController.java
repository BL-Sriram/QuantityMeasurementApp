package com.quantity_measurement.auth_service.user.controller;

import com.quantity_measurement.auth_service.user.dto.AuthResponse;
import com.quantity_measurement.auth_service.user.dto.LoginRequest;
import com.quantity_measurement.auth_service.user.dto.RegisterRequest;
import com.quantity_measurement.auth_service.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/success")
    public String success(@RequestParam String token) {

        return token;
    }
}