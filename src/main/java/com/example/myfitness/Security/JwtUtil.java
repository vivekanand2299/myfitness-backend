package com.example.myfitness.Security;

import com.example.myfitness.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "myfitness_super_secret_key_987654321";
    private final long EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 days

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(
                Jwts.parserBuilder()
                        .setSigningKey(SECRET.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject()
        );
    }
}

