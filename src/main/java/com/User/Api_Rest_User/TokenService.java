package com.User.Api_Rest_User;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
    @Value("${security.jwt.token.secret-key}")
    private String expiration;

    @Value("${security.jwt.token.expire-length}")
    private String secret;

    public String gerarToken(User user) {
        Date agora = new Date();
        Date expira = new Date(agora.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("User")
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .setExpiration(expira)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }
    }
