package com.example.quantity_measurement_app.security;

import com.example.quantity_measurement_app.user.entity.User;
import com.example.quantity_measurement_app.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    // Repository used to store and fetch user data
    private final UserRepository userRepository;

    // Service used for generating JWT token
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Get authenticated Google user details
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        // Extract email and name from Google account
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // Check if user already exists in database
        Optional<User> existingUser = userRepository.findByEmail(email);

        User user;

        if (existingUser.isPresent()) {

            // Existing user found
            user = existingUser.get();

        } else {

            // Create new user for first-time Google login
            user = new User();

            user.setName(name);
            user.setEmail(email);

            // Password kept empty because login is handled by Google
            user.setPassword("");

            // Default role assigned to new Google users
            user.setRole("ROLE_USER");

            // Store login provider information
            user.setProvider("GOOGLE");

            // Save new user into database
            userRepository.save(user);
        }

        /*
         * Create Spring Security UserDetails object
         * for generating JWT token
         */
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password("")
                        .authorities(user.getRole())
                        .build();

        // Generate JWT token
        String token = jwtService.generateToken(userDetails);

        /*
         * Redirect user after successful login
         * along with generated JWT token
         */
        response.sendRedirect("http://localhost:8080/auth/success?token=" + token);
    }
}