package org.cafevariome.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.cafevariome.model.AccessRequest;
import org.cafevariome.util.ClientConfig;
import org.cafevariome.util.HttpUtil;

public class AccessRequestAPI extends ClientAPI{
    public AccessRequestAPI(ClientConfig config, HttpUtil httpUtil) {
        super(config, httpUtil);
    }

    public boolean createAccessRequest(AccessRequest accessRequest){
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
}
