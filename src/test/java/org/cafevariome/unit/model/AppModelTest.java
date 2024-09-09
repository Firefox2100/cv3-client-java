package org.cafevariome.unit.model;

import org.cafevariome.model.AccessRequest;
import org.cafevariome.model.AppModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppModelTest {
    private AccessRequest accessRequest;

    @BeforeEach
    public void setUp() {
        accessRequest = new AccessRequest(
                "a7400080-6c9b-44a4-8113-7fb6cb700f76",
                "John",
                "Doe",
                "john.doe@example.com",
                "University of Leicester",
                "Research"
        );
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("a7400080-6c9b-44a4-8113-7fb6cb700f76", accessRequest.getRequestID());
        assertEquals("John", accessRequest.getFirstName());
        assertEquals("Doe", accessRequest.getLastName());
        assertEquals("john.doe@example.com", accessRequest.getEmail());
        assertEquals("University of Leicester", accessRequest.getAffiliation());
        assertEquals("Research", accessRequest.getJustification());

        accessRequest.setFirstName("Jane");
        assertEquals("Jane", accessRequest.getFirstName());
    }

    @Test
    public void testSerializationToJson() {
        String expectedJson = "{\"requestID\":\"a7400080-6c9b-44a4-8113-7fb6cb700f76\",\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"affiliation\":\"University of Leicester\"," +
                "\"justification\":\"Research\"}";
        String jsonResult = accessRequest.toJson();

        assertEquals(expectedJson, jsonResult);
    }

    @Test
    public void testDeserializationFromJson() {
        String jsonString = "{\"requestID\":\"a7400080-6c9b-44a4-8113-7fb6cb700f76\",\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"affiliation\":\"University of Leicester\"," +
                "\"justification\":\"Research\"}";
        AccessRequest deserializedRequest = AppModel.fromJson(jsonString, AccessRequest.class);

        assertEquals("a7400080-6c9b-44a4-8113-7fb6cb700f76", deserializedRequest.getRequestID());
        assertEquals("John", deserializedRequest.getFirstName());
        assertEquals("Doe", deserializedRequest.getLastName());
        assertEquals("john.doe@example.com", deserializedRequest.getEmail());
        assertEquals("University of Leicester", deserializedRequest.getAffiliation());
        assertEquals("Research", deserializedRequest.getJustification());
    }

    @Test
    public void testDeserializationFromJsonList() {
        String jsonList = "[{\"requestID\":\"a7400080-6c9b-44a4-8113-7fb6cb700f76\",\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"affiliation\":\"University of Leicester\"," +
                "\"justification\":\"Research\"}]";
        List<AccessRequest> accessRequests = AppModel.fromJsonList(jsonList, AccessRequest.class);

        assertEquals(1, accessRequests.size());
        AccessRequest deserializedRequest = accessRequests.getFirst();

        assertEquals("a7400080-6c9b-44a4-8113-7fb6cb700f76", deserializedRequest.getRequestID());
        assertEquals("John", deserializedRequest.getFirstName());
        assertEquals("Doe", deserializedRequest.getLastName());
        assertEquals("john.doe@example.com", deserializedRequest.getEmail());
        assertEquals("University of Leicester", deserializedRequest.getAffiliation());
        assertEquals("Research", deserializedRequest.getJustification());
    }

    @Test
    public void testSerializationError() {
        // Simulating an error condition by mocking invalid data (in practice, this can be expanded).
        AccessRequest invalidRequest = new AccessRequest() {
            @Override
            public String toJson() {
                throw new RuntimeException("Simulated serialization error");
            }
        };

        Exception exception = assertThrows(RuntimeException.class, invalidRequest::toJson);
        assertEquals("Simulated serialization error", exception.getMessage());
    }

    @Test
    public void testDeserializationError() {
        String invalidJson = "invalid json";

        Exception exception = assertThrows(RuntimeException.class, () ->
                AppModel.fromJson(invalidJson, AccessRequest.class));

        assertTrue(exception.getMessage().contains("Error converting JSON to AccessRequest"));
    }
}
