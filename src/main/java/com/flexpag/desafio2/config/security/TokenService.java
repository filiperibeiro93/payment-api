package com.flexpag.desafio2.config.security;

import com.flexpag.desafio2.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();

        return Jwts.builder()
                .setIssuer("API para agendamento de pagamentos")
                .setSubject(logged.getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUser(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
