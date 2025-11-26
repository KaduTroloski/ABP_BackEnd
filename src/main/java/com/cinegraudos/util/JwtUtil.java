package com.cinegraudos.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtil {
    private static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION = 1000 * 60 * 60;

    public static String getToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String valideteToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Token not valid");
        }
    }

    public static List<String> getRoles(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("roles", List.class);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Token not valid");
        }
    }
}
