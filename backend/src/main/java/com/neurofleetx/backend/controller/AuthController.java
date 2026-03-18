package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.dto.*;
import com.neurofleetx.backend.model.User;
import com.neurofleetx.backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔐 REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        try {
            User user = authService.register(
                    request.getName(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getPhone(),
                    request.getRole()
            );

            return ResponseEntity.ok(
                    new ApiResponse(true, "User registered successfully", user)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {
            String token = authService.login(
                    request.getEmail(),
                    request.getPassword()
            );

            return ResponseEntity.ok(
                    new ApiResponse(true, "Login successful", token)
            );

        } catch (Exception e) {

            // ❌ Invalid credentials → return 401 instead of 403
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, e.getMessage(), null));
        }
    }
}
