package com.neurofleetx.backend.config;

import com.neurofleetx.backend.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ⭐ Skip JWT check for public APIs
        if (path.startsWith("/api/auth") ||
            path.startsWith("/api/bookings") ||
            path.startsWith("/api/vehicles") ||
            path.startsWith("/api/routes") ||
            path.startsWith("/api/maintenance") ||
            path.startsWith("/api/telemetry")) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        // 🔓 No token → continue (no blocking)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);

            Claims claims = jwtService.extractClaims(token);

            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            if (email != null && role != null) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.singletonList(
                                        new SimpleGrantedAuthority("ROLE_" + role)
                                )
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {
            // ❌ Invalid token → clear context but allow request
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}