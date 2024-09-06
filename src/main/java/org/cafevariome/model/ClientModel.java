package org.cafevariome.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientModel {
    private static final ObjectMapper mapper = new ObjectMapper();

    public String toJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting " + this.getClass().getSimpleName() + " to JSON: " + e.getMessage(), e);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to " + clazz.getSimpleName() + ": " + e.getMessage(), e);
        }
    }

    public static <T> List<T> fromJsonList(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to List<" + clazz.getSimpleName() + ">: " + e.getMessage(), e);
        }
    }
}
