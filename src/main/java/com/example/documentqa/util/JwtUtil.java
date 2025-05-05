package com.example.documentqa.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Generate a secure key for HS256 algorithm
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createJwt(String username) {
        // Build the JWT token using the secure key
        return Jwts.builder()
                .setSubject(username)
                .signWith(key)
                .compact();
    }

    public String getSecretKey() {
        // Return the secure key (you can use this for further purposes if needed)
        return key.toString();
    }

	public String generateToken(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
