
package com.app.admin_backend_v1.security;

import com.app.admin_backend_v1.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get the Authorization header
        String header = request.getHeader("Authorization");
        String email = null;
        String jwtToken = null;

        // Check if the header contains a Bearer token
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
            try {
                // Validate the token using JwtUtil
                if (JwtUtil.validateToken(jwtToken)) {
                    email = JwtUtil.getEmailFromToken(jwtToken);
                    logger.debug("Valid JWT token for email: {}", email);
                } else {
                    logger.warn("Invalid JWT token: {}", jwtToken);
                }
            } catch (Exception e) {
                logger.error("JWT token validation failed: {}", e.getMessage());
            }
        } else {
            logger.debug("No JWT token found in request header or invalid format: {}", header);
        }

        // If token is valid and no authentication exists, set the security context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Authentication set for email: {}", email);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
