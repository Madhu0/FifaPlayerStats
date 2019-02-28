package exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import utils.JsonUtils;


@Getter
@Setter
public class CustomException extends Exception {
    private Integer errorCode;
    private Integer httpStatusCode;
    private JsonNode errors;


    public CustomException(Integer errorCode, String message, Integer httpStatusCode) {
        this.errorCode = errorCode;
        this.errors = getJsonNodeFromErrorMessage(message);
        this.httpStatusCode = httpStatusCode;
    }

    public CustomException(Integer errorCode, JsonNode errors, Integer httpStatusCode) {
        this.errors = errors;
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    private JsonNode getJsonNodeFromErrorMessage(String message) {
        ObjectNode node = JsonUtils.getEmptyJsonNode();
        node.put("message", message);
        return node;
    }

    public JsonNode asJson() {
        ObjectNode resp = JsonUtils.getEmptyJsonNode();
        resp.put("errorCode", errorCode);
        resp.put("errors", errors);
        return resp;
    }
}
