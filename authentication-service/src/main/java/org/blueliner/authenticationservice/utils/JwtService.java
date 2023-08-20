package org.blueliner.authenticationservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.blueliner.authenticationservice.model.UserCredential;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String generateToken(UserCredential userCredential) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userCredential);
    }

    private String createToken(Map<String, Object> claims, UserCredential userCredential) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userCredential.getEmail())
                .claim("email", userCredential.getEmail())
                .claim("name", userCredential.getName())
                .setSubject(userCredential.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
