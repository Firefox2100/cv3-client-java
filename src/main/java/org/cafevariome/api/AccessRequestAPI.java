package org.cafevariome.api;

import org.cafevariome.model.AccessRequest;
import org.cafevariome.util.AppConfig;
import org.cafevariome.util.HttpUtil;

import java.util.List;

public class AccessRequestAPI extends ClientAPI {
    public AccessRequestAPI(AppConfig config, HttpUtil httpUtil) {
        super(config, httpUtil);
    }

    public boolean create(AccessRequest accessRequest) {
        try {
            return httpUtil.postModel(config.getAdminURI() + "/access_requests/", accessRequest);
        } catch (Exception e) {
            config.logger.error("Error creating access request: {}", e.getMessage());
            return false;
        }
    }

    public boolean update(AccessRequest accessRequest) {
        try {
            return httpUtil.putModel(
                    config.getAdminURI() + "/access_requests/" + accessRequest.getRequestID(),
                    accessRequest
            );
        } catch (Exception e) {
            config.logger.error("Error updating access request: {}", e.getMessage());
            return false;
        }
    }

    public List<AccessRequest> getAll() {
        try {
            return httpUtil.getModels(config.getAdminURI() + "/access_requests/", AccessRequest.class);
        } catch (Exception e) {
            config.logger.error("Error getting access requests: {}", e.getMessage());
            return null;
        }
    }

    public AccessRequest get(String requestID) {
        try {
            return httpUtil.getModel(config.getAdminURI() + "/access_requests/" + requestID, AccessRequest.class);
        } catch (Exception e) {
            config.logger.error("Error getting access request: {}", e.getMessage());
            return null;
        }
    }

    public boolean delete(String requestID) {
        try {
            return httpUtil.deleteModel(config.getAdminURI() + "/access_requests/" + requestID);
        } catch (Exception e) {
            config.logger.error("Error deleting access request: {}", e.getMessage());
            return false;
        }
    }
}
