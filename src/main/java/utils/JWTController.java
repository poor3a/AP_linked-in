package utils;

import com.sun.net.httpserver.HttpExchange;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;


public class JWTController {
    private static final Key key = Keys.hmacShaKeyFor("ParsaASA-PooriaSAD-LINKED-IN".getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 1_000 * 60 * 60  * 3; // 3 hour
    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    public static String createToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, algorithm)
                .compact();
        return token;
    }

    public static String verifyToken(HttpExchange exchange) {
        try {
            String token = exchange.getRequestHeaders().get("JWT").get(0);
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

}