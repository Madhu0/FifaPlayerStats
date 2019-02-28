package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getObjectFromJson(JsonNode node, Class<T> className) throws JsonProcessingException {
        return objectMapper.treeToValue(node, className);
    }

    public static ObjectNode getEmptyJsonNode() {
        return objectMapper.createObjectNode();
    }
}
