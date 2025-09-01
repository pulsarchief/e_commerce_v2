package com.priyanshu.e_commerce_v2.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.timeout}")
    private Long jwtTimeout;

    public String createJwtToken(UserDetails userDetails) {

        return Jwts
                .builder()
                .signWith(getKeys())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtTimeout))
                .compact();

    }

    public String getUsername(String token) {

        return Jwts
                .parser()
                .verifyWith(getKeys())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public boolean isValid(String token) {

        return Jwts
                .parser()
                .verifyWith(getKeys())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .after(new Date());

    }

    private SecretKey getKeys() {
        return Keys
                .hmacShaKeyFor(jwtKey
                        .getBytes());
    }
}
