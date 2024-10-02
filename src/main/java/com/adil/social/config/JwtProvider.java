package com.adil.social.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder()
                .setIssuer("Ray")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000)) //expires after 24hrs
                .claim("email",auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt) {

        //as we are getting the jwt string as "Bearer Token"
        //but we only need the "Token" so we remove the "Bearer " part

        //jwt = jwt.replace("Bearer ", ""); or
        jwt = jwt.substring(7); //removes first 7 letters

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
