package controllers;

import applications.LoginApplication;
import applications.RegisterUserApplication;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;
import io.swagger.annotations.*;

import javax.inject.Inject;

import java.util.concurrent.CompletionStage;

@Api(value = "User", description = "User related flows")
public class UserController extends Controller {


//    private DatabaseExecutionContext databaseExecutionContext;
//    private ApplicationExecutionContext applicationExecutionContext;
//    private FormFactory formFactory;
    private RegisterUserApplication registerUserApplication;
    private LoginApplication loginApplication;

    @Inject
    public UserController(RegisterUserApplication rua, LoginApplication la) {
        registerUserApplication = rua;
        loginApplication = la;
    }

    @ApiOperation(httpMethod = "POST", nickname = "Register", value = "Create new user")
    @ApiImplicitParams(
            @ApiImplicitParam(dataType = "requests.RegisterUserRequest", name="User", required = true, paramType = "Body")
    )
    @ApiResponse(response = responses.UserDetailsResponse.class, code = 200, message = "User created successfully")
    public CompletionStage<Result> register() {
        JsonNode input = request().body().asJson();
        registerUserApplication.init(input);
        return registerUserApplication.execute();
    }

    @ApiOperation(httpMethod = "POST", nickname = "Login", value = "Log into your account")
    @ApiImplicitParams(
            @ApiImplicitParam(dataType = "requests.LoginRequest", name="Credentials", required = true, paramType = "Body")
    )
    @ApiResponse(response = responses.LoginResponse.class, code = 200, message = "User created successfully")
    public CompletionStage<Result> login() {
        JsonNode input = request().body().asJson();
        loginApplication.init(input);
        return loginApplication.execute();
    }

//    @ApiOperation(httpMethod = "POST", nickname = "registerSync", value = "Create new user in sync")
//    @ApiImplicitParam(dataType = "models.User", name="User", required = true, paramType = "Body")
//    @ApiResponse(response = models.User.class, code=200, message = "User created successfully")
//    public CompletionStage<Result> registerSync() {
//        Form<User> userForm = formFactory.form(User.class).bindFromRequest(request());
//        if (userForm.hasErrors()) {
//            return CompletableFuture.completedFuture(badRequest(userForm.errorsAsJson()));
//        }
//        User user = userForm.get();
//        return CompletableFuture.supplyAsync(() -> {
//            User response = userRepository.createSync(user);
//            return ok(Json.toJson(response));
//        }, databaseExecutionContext);
//    }
}
