package com.example.Zapy.config;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.Zapy.Repository.AppUserRepository;
import com.example.Zapy.model.User;

@Configuration
public class SecurityConfig {

    private final AppUserRepository userRepository;

    public SecurityConfig(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // easier for API + SPA (you can tighten later)
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/oauth2/**", "/login/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(oauth2SuccessHandler()) // 👈 save user + redirect
            )
            .logout(logout -> logout
                .logoutSuccessUrl("http://localhost:5173/") // on logout, go back to frontend
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler oauth2SuccessHandler() {
        return (request, response, authentication) -> {

            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attrs = oAuth2User.getAttributes();

            // Typical Google attributes: "sub", "name", "email", "picture"
            String googleId = (String) attrs.get("sub");
            String email    = (String) attrs.get("email");
            String name     = (String) attrs.get("name");
            String picture  = (String) attrs.get("picture");

            User user = userRepository.findByGoogleId(googleId)
                .orElseGet(() -> {
                    User u = new User();
                    u.setGoogleId(googleId);
                    u.setEmail(email);
                    u.setCreatedAt(Instant.now());
                    return u;
                });

            user.setName(name);
            user.setPictureUrl(picture);
            user.setLastLoginAt(Instant.now());

            userRepository.save(user);

            // redirect to your frontend after saving
            response.sendRedirect("http://localhost:5173/auth/success");
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // important for cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
