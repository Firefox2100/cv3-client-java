package org.cafevariome.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cafevariome.model.AccessRequest;
import org.cafevariome.util.ClientConfig;
import org.cafevariome.util.HttpUtil;

public class AccessRequestAPI extends ClientAPI {
    public AccessRequestAPI(ClientConfig config, HttpUtil httpUtil) {
        super(config, httpUtil);
    }

    public boolean createAccessRequest(AccessRequest accessRequest) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;

        try {
            jsonString = mapper.writeValueAsString(accessRequest);
        } catch (JsonProcessingException e) {
            config.logger.error("Error converting AccessRequest object to JSON: {}", e.getMessage());
            return false;
        }

        try {
            httpUtil.post(config.getAdminURI() + "/access_requests/", jsonString);
        } catch (Exception e) {
            config.logger.error("Error creating access request: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public boolean updateAccessRequest(AccessRequest accessRequest) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;

        try {
            jsonString = mapper.writeValueAsString(accessRequest);
        } catch (JsonProcessingException e) {
            config.logger.error("Error converting AccessRequest object to JSON: {}", e.getMessage());
            return false;
        }

        try {
            httpUtil.put(config.getAdminURI() + "/access_requests/" + accessRequest.getRequestID(), jsonString);
        } catch (Exception e) {
            config.logger.error("Error updating access request: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public AccessRequest[] getAccessRequests() {
        try {
            String response = httpUtil.get(config.getAdminURI() + "/access_requests/").body();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, AccessRequest[].class);
        } catch (Exception e) {
            config.logger.error("Error getting access requests: {}", e.getMessage());
            return null;
        }
    }

    public AccessRequest getAccessRequest(String requestID) {
        try {
            String response = httpUtil.get(config.getAdminURI() + "/access_requests/" + requestID).body();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, AccessRequest.class);
        } catch (Exception e) {
            config.logger.error("Error getting access request: {}", e.getMessage());
            return null;
        }
    }

    public boolean deleteAccessRequest(String requestID) {
        try {
            httpUtil.delete(config.getAdminURI() + "/access_requests/" + requestID);
        } catch (Exception e) {
            config.logger.error("Error deleting access request: {}", e.getMessage());
            return false;
        }

        return true;
    }
}
