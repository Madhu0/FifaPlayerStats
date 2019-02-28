package responses;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import play.libs.Json;


@Getter
@Setter
public abstract class BaseResponse {
    private String requestId;

    public JsonNode asJson() {
        return Json.toJson(this);
    };
}
