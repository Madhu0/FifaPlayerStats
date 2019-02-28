package applications;

import authentication.AuthParams;
import authentication.AuthTokenCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.CustomException;
import models.User;
import play.data.FormFactory;
import repository.ApplicationExecutionContext;
import repository.DatabaseExecutionContext;
import repository.UserRepository;
import requests.BaseRequest;
import requests.LoginRequest;
import responses.BaseResponse;
import responses.LoginResponse;
import utils.JsonUtils;
import utils.Password;

import javax.inject.Inject;
import java.util.concurrent.CompletionException;

public class LoginApplication extends BaseApplication {

    private UserRepository userRepository;

    @Inject
    public LoginApplication(DatabaseExecutionContext dec, ApplicationExecutionContext aec, FormFactory ff, UserRepository repo) {
        super(dec, aec, ff);
        this.userRepository = repo;
    }

    public void init(JsonNode input) {
        super.init(input, "LoginApplication");
    }

    protected BaseRequest getRequest() throws JsonProcessingException {
        return JsonUtils.getObjectFromJson(this.getInput(), LoginRequest.class);
    }

    protected BaseResponse runInternal(BaseRequest req) throws CompletionException {
        try {
            LoginRequest request = (LoginRequest) req;
            User user = userRepository.getUserByEmail(request.getEmail());
            if (user == null) {
                throw new CustomException(401, "EmailId " + request.getEmail() + " doesn't exist", 401);
            }
            if (!user.getPasswordHash().equals(Password.encryptMd5(request.getPassword()))) {
                throw new CustomException(401, "Email and password mismatch", 401);
            }
            LoginResponse response = new LoginResponse();
            response.setRequestId(request.getRequestId());
            AuthParams authParams = new AuthParams(user.getEmail(), user.getId());
            response.setJwtToken(AuthTokenCreator.createAuthToken(authParams));
            response.setUserDetails(user);
            return response;
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
}
