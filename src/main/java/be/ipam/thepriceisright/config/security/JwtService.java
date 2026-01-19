package be.ipam.thepriceisright.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtService {
    private static final String SECRET =
            "ZLnmgrJxz7LQGZ14thXXNAJoKLHxFsZ22YXD5aM1D4E";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateAccessToken(UserDetails user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(900))) // 15 min
                .claim("type", "access")
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(UserDetails user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(60L * 60 * 24 * 7))) // 7 jours
                .claim("type", "refresh")
                .signWith(key)
                .compact();
    }

    public boolean isAccessToken(String token) {
        return "access".equals(
                Jwts.parser().verifyWith(key).build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .get("type", String.class)
        );
    }

    public boolean isTokenValid(String token) {
        var claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
        Date exp = claims.getExpiration();
        return exp != null && exp.after(new Date());
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}