package com.example.Hotel.Configration;



import java.io.IOException;

import com.example.Hotel.Services.JWTService;
import com.example.Hotel.Services.uerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService; // Optional: specify visibility

    @Autowired
    private uerService userService; // Corrected service name

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        // Check authentication header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the token from the header
        jwt = authHeader.substring(7);
        // Extract the username from the token (username is the email)
        userName = jwtService.extractUserName(jwt);

        // Authenticate if not already authenticated
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userService.loadUserByUsername(userName);
            // Check if token is valid
            if (jwtService.isTokenValid(jwt, user)) {
                // Update the security context holder
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities());
                // Set additional details such as user's IP address, browser, or other attributes
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Call the next filter
        filterChain.doFilter(request, response);
    }
}
