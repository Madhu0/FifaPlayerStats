package requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest extends BaseRequest {
    private String name;
    private String email;
    private String password;
}
