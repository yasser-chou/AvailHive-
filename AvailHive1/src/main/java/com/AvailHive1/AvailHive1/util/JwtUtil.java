package com.AvailHive1.AvailHive1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.DecodeException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    // The SECRET key should be kept safe and should not be exposed in your source code for production applications.
    public static final String SECRET = "d3b5019d5dff8eb826057a7818372e436be8e76ab9cffd772b63f98c3ea28802";

    // This method creates a JWT token for a given user.
    public String createToken(Map<String, Object> claims, String userName){
        return Jwts.builder()
                .setClaims(claims) // Set the custom claims for the token
                .setSubject(userName) // Set the subject, in this case, the userName
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue date as current date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Set the expiration date as 30 minutes from the issue date
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact(); // Build the token into a URL-safe string
    }

    // This method converts the SECRET into a signing key for HS256.
    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .parseClaimsJwt(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}