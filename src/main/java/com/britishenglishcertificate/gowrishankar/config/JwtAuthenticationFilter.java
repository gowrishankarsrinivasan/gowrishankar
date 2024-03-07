package com.britishenglishcertificate.gowrishankar.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.britishenglishcertificate.gowrishankar.utils.JwtUtil;

import org.springframework.http.HttpHeaders;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // Autowired bean for JWT utility
    private final UserDetailsService userDetailsService; // Autowired bean for UserDetailsService

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // Get Authorization header from request
        final String token;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // Check if Authorization header is present and
                                                                       // starts with "Bearer "
            filterChain.doFilter(request, response); // If not, proceed with the filter chain
            return;
        }

        token = authHeader.substring(7); // Extract JWT token from Authorization header
        userEmail = jwtUtil.extractUsername(token); // Extract username (user email) from JWT token

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // If user email is present and there is no authentication in the
            // SecurityContextHolder
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); // Load user details from
                                                                                             // UserDetailsService

            if (jwtUtil.isTokenValid(token, userDetails)) {
                // If JWT token is valid for the user
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userEmail, null,
                        userDetails.getAuthorities()); // Create authentication token

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Set authentication
                                                                                                  // details
                SecurityContextHolder.getContext().setAuthentication(authToken); // Set authentication in
                                                                                 // SecurityContextHolder
            }
        }

        filterChain.doFilter(request, response); // Proceed with the filter chain
    }

}
