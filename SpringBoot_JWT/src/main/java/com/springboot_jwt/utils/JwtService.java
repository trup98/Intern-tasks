package com.springboot_jwt.utils;

import com.springboot_jwt.dto.RefreshTokenRequest;
import com.springboot_jwt.dto.ResponseToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public ResponseToken generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        /*claims.put("role","ADMIN");*/

        return createToken(claims, userName);

    }

    private ResponseToken createToken(Map<String, Object> claims, String userName) {
        String accessToken = Jwts.builder().
                setClaims(claims).
                setSubject(userName).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)).
                signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        String refreshToken = Jwts.builder().
                setClaims(claims).
                setSubject(userName).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).
                signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        return new ResponseToken(accessToken,refreshToken);
    }





    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(java.lang.String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.
                parserBuilder().
                setSigningKey(getSignKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public ResponseToken createRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        String token = refreshTokenRequest.getToken();
        String userName = extractUserName(token);
        return generateToken(userName);
    }
}
