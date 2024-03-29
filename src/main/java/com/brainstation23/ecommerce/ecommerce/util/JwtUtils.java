package com.brainstation23.ecommerce.ecommerce.util;


import com.brainstation23.ecommerce.ecommerce.model.security.SecureUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    private static final String INVALID_TOKEN = "Invalid JWT token: {}";
    private static final String EXPIRED_TOKEN = "JWT token is expired: {}";
    private static final String UNSUPPORTED_TOKEN = "JWT token is unsupported: {}";
    private static final String EMPTY_TOKEN = "JWT claims string is empty: {}";
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private  int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        SecureUserDetails userPrincipal = (SecureUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key()).build()
                    .parse(authToken);

            return true;
        } catch (MalformedJwtException e) {
            log.error(INVALID_TOKEN, e.getMessage());
            throw new JwtException(INVALID_TOKEN + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error(EXPIRED_TOKEN, e.getMessage());
            throw new JwtException(EXPIRED_TOKEN + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error(UNSUPPORTED_TOKEN, e.getMessage());
            throw new JwtException(UNSUPPORTED_TOKEN + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(EMPTY_TOKEN, e.getMessage());
            throw new JwtException(EMPTY_TOKEN +  e.getMessage());
        }
    }

}
