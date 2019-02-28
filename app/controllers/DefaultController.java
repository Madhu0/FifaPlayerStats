package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;

public class DefaultController extends Controller {
    public JsonNode getResponse(JsonNode data) {
        ObjectNode json = Json.newObject();
        json.set(this.getClass().toString(), data);
        return json;
    }
}
