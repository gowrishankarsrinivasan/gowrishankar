package com.britishenglishcertificate.gowrishankar.config;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.HEADERS;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.LOGOUT;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.METHODS;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.ORIGINS;
import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.WHITELIST_URL;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // .csrf(AbstractHttpConfigurer::disable): Disables CSRF protection.
                .csrf(AbstractHttpConfigurer::disable)
                // .cors(cors -> corsConfigurationSource()): Configures CORS
                // (Cross-Origin Resource Sharing) settings using the corsConfigurationSource
                // bean.
                .cors(cors -> corsConfigurationSource())
                // Configures authorization rules. Requests matching WHITELIST_URL
                // are permitted without authentication, while any other requests must be
                // authenticated.
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(WHITELIST_URL)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                // Configures session management to be stateless,
                // meaning that the server does not store session information.
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                // Sets the custom authentication provider.
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl(AUTH + LOGOUT)
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(ORIGINS);
        corsConfiguration.setAllowedHeaders(HEADERS);
        corsConfiguration.setAllowedMethods(METHODS);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
