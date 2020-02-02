package com.library.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenUtil {
    @Value("${auth.expiration}")
    private long expiration;

    @Value("${auth.secret}")
    private String secret;

    @Value("${auth.prefix}")
    private String tokenPrefix;

    @Value("${auth.header}")
    private String headerString;
    //    private UserRepository userRepository;
    public String addAuthentication(JwtUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        claims.put("roles", user.getAuthorities());
        claims.put("id", user.getId());
        // We generate a token now.
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
//        token = AES.encrypt(token);
        return token;
    }

    public String getEmail(String token){
//        token = AES.decrypt(token);
        System.out.println(token);
        String email;
        try {
            email = getClaims(token).getSubject();
            return email;
        }
        catch (Exception ex){
            return null;
        }
    }

    private boolean isTokenExpired(String token){
        try {
            Date expirationDate = getClaims(token).getExpiration();
            return expirationDate.before(new Date(System.currentTimeMillis()));
        }
        catch (Exception e){
            return false;
        }
    }



    Boolean isTokenValid(String token, UserDetails userDetails) {
//        token = AES.decrypt(token);
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getEmail(token);
//        final String username = getEmail(AES.encrypt(token));
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private Claims getClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
