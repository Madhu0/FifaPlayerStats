package application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.CustomException;
import lombok.Getter;
import lombok.Setter;
import play.Logger;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Results;
import repository.ApplicationExecutionContext;
import repository.DatabaseExecutionContext;
import requests.BaseRequest;
import responses.BaseResponse;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

@Getter
@Setter
public abstract class BaseApplication {
    private JsonNode input;
    private String apiName;
    private String requestId;
    private DatabaseExecutionContext databaseExecutionContext;
    private ApplicationExecutionContext applicationExecutionContext;
    private FormFactory formFactory;

    public BaseApplication(DatabaseExecutionContext dec, ApplicationExecutionContext aec, FormFactory ff) {
        databaseExecutionContext = dec;
        applicationExecutionContext = aec;
        formFactory = ff;
    }

    public void init(JsonNode input, String apiName) {
        Logger.info("In " + apiName);
        this.input = input;
        this.apiName = apiName;
    }

    public CompletionStage<Result> execute() {
        try {
            BaseRequest request = getRequest();
            return supplyAsync(() -> {
                BaseResponse resp = runInternal(request);
                return Results.ok(resp.asJson());
            }).exceptionally((e) -> {
                Logger.error("[Error] [" + this.apiName + "] ", e);
                if (e instanceof CustomException) {
                    CustomException actualExec = (CustomException) e.getCause();
                    return Results.status(actualExec.getHttpStatusCode(), actualExec.asJson());
                }
                return Results.internalServerError(e.getMessage());
            });
        } catch (Exception e) {
            Logger.error("[Error] [" + this.apiName + "] ", e);
            return CompletableFuture.completedFuture(Results.internalServerError(e.getMessage()));
        }
    }

    protected abstract BaseRequest getRequest() throws JsonProcessingException;
    protected abstract BaseResponse runInternal(BaseRequest request) throws CompletionException;
}
