package com.flexpag.desafio2.config.security;

import com.flexpag.desafio2.models.User;
import com.flexpag.desafio2.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repository;

    public TokenAuthFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenRecover(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid) {
            userAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    private void userAuthentication(String token) {
        Long idUser = tokenService.getIdUser(token);
        User user = repository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String tokenRecover(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }


}
