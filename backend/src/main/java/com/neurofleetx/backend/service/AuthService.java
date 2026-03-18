package com.neurofleetx.backend.service;

import com.neurofleetx.backend.model.Role;
import com.neurofleetx.backend.model.User;
import com.neurofleetx.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // 🔐 REGISTER
    public User register(String name, String email, String password,
                         String phone, String role) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // ✅ Hash password correctly
        user.setPassword(passwordEncoder.encode(password));

        user.setPhone(phone);
        user.setRole(Role.valueOf(role.toUpperCase())); // safer

        return userRepository.save(user);
    }

    // 🔐 LOGIN (returns JWT token)
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        // ✅ Compare hashed password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // ✅ Generate JWT token
        return jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
