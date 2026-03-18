package com.neurofleetx.backend.config;

import com.neurofleetx.backend.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔓 Disable CSRF for REST APIs
            .csrf(csrf -> csrf.disable())

            // 🌐 Enable CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // ❌ Disable default login systems
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable())

            // 🔐 Stateless JWT (no sessions)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // ✅ Allow preflight requests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // ⭐ PUBLIC APIs
                .requestMatchers("/api/auth/**").permitAll()

                // 🚗 Fleet & Telemetry
                .requestMatchers("/api/vehicles/**").permitAll()
                .requestMatchers("/api/telemetry/**").permitAll()
                .requestMatchers("/api/routes/**").permitAll()

                // 🔧 Maintenance & Alerts
                .requestMatchers("/api/maintenance/**").permitAll()
                .requestMatchers("/api/alerts/**").permitAll()

                // 📦 Booking Module
                .requestMatchers("/api/bookings/**").permitAll()

                // 📊 ADMIN DASHBOARD
                .requestMatchers("/api/admin/dashboard/**").permitAll()

                // 🌆 URBAN INSIGHTS (⭐ ADD THIS)
                .requestMatchers("/api/insights/**").permitAll()

                // 🔐 Role-based APIs
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/fleet/**").hasRole("FLEET_MANAGER")

                // 🔒 Everything else requires authentication
                .anyRequest().authenticated()
            )

            // 🔥 JWT Filter
            .addFilterBefore(
                new JwtAuthFilter(jwtService),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    // 🌐 CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    // 🔐 Password Encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}