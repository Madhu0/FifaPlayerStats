package authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.time.Instant;

// TODO: write an annotation
public class AuthTokenVerification {
    public static AuthParams verifyToken(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey("thisissparta".getBytes()).parseClaimsJws(token).getBody();
        AuthParams params = new AuthParams();
        if (claims.containsKey("id")) {
            params.setId((Long)claims.get("id"));
        }
        if (claims.containsKey("email")) {
            params.setEmail((String)claims.get("email"));
        }
        if (claims.containsKey("expirationTime")) {
            params.setEmail((String)claims.get("expirationTime"));
            if ((Long)claims.get("expirationTime") < Instant.now().getEpochSecond()) {
                throw new Exception("Token Expired");
            }
        }
        return params;
//        return (Long)claims.get("expirationTime") < Instant.now().getEpochSecond();
    }
}
