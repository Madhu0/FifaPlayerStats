package authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthTokenCreator {
    public static String createAuthToken(AuthParams params) {
        JwtBuilder builder = Jwts.builder()
                .setSubject("AuthToken")
                .claim("email", params.getEmail())
                .claim("id", params.getId())
                .signWith(SignatureAlgorithm.HS256, "thisissparta".getBytes())
                .claim("expirationTime", params.getExpirationTime());
        return builder.compact();
    }
}
