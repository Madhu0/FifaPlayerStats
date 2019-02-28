package responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends UserDetailsResponse {
    protected String jwtToken;
}
