package authentication;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuthParams {
    private String email;
    private Long id;
    private Instant expirationTime;

    public AuthParams() {}

    public AuthParams(String email, Long id) {
        this.email = email;
        this.id = id;
        this.expirationTime = Instant.ofEpochSecond(Instant.now().getEpochSecond() + 7 * 24 * 60 * 60);
    }
}
