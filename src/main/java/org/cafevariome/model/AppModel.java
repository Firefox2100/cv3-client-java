package org.cafevariome.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class AppModel {
    private static final ObjectMapper mapper = new ObjectMapper();

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

    public String toJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting " + this.getClass().getSimpleName() + " to JSON: " + e.getMessage(), e);
        }
    }
}
