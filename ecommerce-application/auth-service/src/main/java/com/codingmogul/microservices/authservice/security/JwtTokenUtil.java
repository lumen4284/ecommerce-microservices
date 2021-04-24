package com.codingmogul.microservices.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expires_in}")
    private String expiresIn;

    public String generateToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(createExpiresDate())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    private Date createExpiresDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    public String getSubjectFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
