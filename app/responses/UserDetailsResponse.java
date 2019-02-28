package responses;

import lombok.Getter;
import lombok.Setter;
import models.User;

import java.time.Instant;

@Getter
@Setter
public class UserDetailsResponse extends BaseResponse {
    private String email;
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant modifiedAt;

    public void setUserDetails(User userObject) {
        this.setEmail(userObject.getEmail());
        this.setId(userObject.getId());
        this.setName(userObject.getName());
        this.setCreatedAt(userObject.getCreatedAt());
        this.setModifiedAt(userObject.getModifiedAt());
    }
}
