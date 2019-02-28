package applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.CustomException;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import repository.ApplicationExecutionContext;
import repository.DatabaseExecutionContext;
import repository.UserRepository;
import requests.BaseRequest;
import requests.RegisterUserRequest;
import responses.BaseResponse;
import responses.UserDetailsResponse;
import utils.JsonUtils;
import utils.Password;

import javax.inject.Inject;
import java.util.concurrent.CompletionException;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class RegisterUserApplication extends BaseApplication {

    private UserRepository userRepository;

    @Inject
    public RegisterUserApplication(DatabaseExecutionContext dec, ApplicationExecutionContext aec, FormFactory ff, UserRepository repo) {
        super(dec, aec, ff);
        this.userRepository = repo;
    }

    public void init(JsonNode input) {
        super.init(input, "RegisterUserApi");
    }

    protected BaseRequest getRequest() throws JsonProcessingException {
        return JsonUtils.getObjectFromJson(this.getInput(), RegisterUserRequest.class);
    }

    protected BaseResponse runInternal(BaseRequest req) throws CompletionException {
        try {
            RegisterUserRequest request = (RegisterUserRequest)req;
            Form<User> userForm = getFormFactory().form(User.class).bind(getInput());
            if (userForm.hasErrors()) {
                throw new CustomException(123, userForm.errorsAsJson(), 400);
            }
            if (userRepository.getUserByEmail(request.getEmail()) != null) {
                throw new CustomException(123, "User with emailId " + request.getEmail() + " already exist", 401);
            }
            User user = userForm.get();
            user.setPasswordHash(Password.encryptMd5(request.getPassword()));
            UserDetailsResponse response = new UserDetailsResponse();
            response.setUserDetails(userRepository.create(userForm.get()));
            response.setRequestId(request.getRequestId());
            return response;
        } catch(Exception e) {
            throw new CompletionException(e);
        }
    }
}
