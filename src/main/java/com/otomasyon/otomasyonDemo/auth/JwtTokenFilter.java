package com.otomasyon.otomasyonDemo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final UserDetailService userDetailService;

    @Autowired
    public JwtTokenFilter(TokenManager tokenManager, UserDetailService userDetailService) {
        this.tokenManager = tokenManager;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String tokenCore = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (tokenCore != null && tokenCore.startsWith("Bearer ")) {
            token = tokenCore.substring(7);
            try {
                email = tokenManager.getUserFromToken(token);
            } catch (Exception e) {
                logger.error("Token parsing error: ", e);
            }
        }

        if (token != null && email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenManager.hasTokenValid(token)) {
                UserDetails user = this.userDetailService.loadUserByUsername(email);
                if (Objects.nonNull(user)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    logger.warn("User not found: " + email);
                }
            } else {
                logger.warn("Invalid token for user: " + email);
            }
        }

        filterChain.doFilter(request, response);
    }
}
