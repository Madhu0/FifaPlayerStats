package requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest extends BaseRequest {
    private String email;
    private String password;
}
