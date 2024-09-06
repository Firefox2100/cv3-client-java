package org.cafevariome.api;

import java.util.List;

import org.cafevariome.model.BeaconEndpoint;
import org.cafevariome.util.AppConfig;
import org.cafevariome.util.HttpUtil;

public class BeaconEndpointAPI extends ClientAPI{
    public BeaconEndpointAPI(AppConfig config, HttpUtil httpUtil) {
        super(config, httpUtil);
    }

    public boolean create(BeaconEndpoint beaconEndpoint) {
        try {
            return httpUtil.postModel(config.getAdminURI() + "/beacon/", beaconEndpoint);
        } catch (Exception e) {
            config.logger.error("Error creating access request: {}", e.getMessage());
            return false;
        }
    }

    public boolean update(BeaconEndpoint beaconEndpoint) {
        try {
            return httpUtil.putModel(
                    config.getAdminURI() + "/beacon/" + beaconEndpoint.getEndpointID(),
                    beaconEndpoint
            );
        } catch (Exception e) {
            config.logger.error("Error updating access request: {}", e.getMessage());
            return false;
        }
    }

    public List<BeaconEndpoint> getAll() {
        try {
            return httpUtil.getModels(config.getAdminURI() + "/beacon/", BeaconEndpoint.class);
        } catch (Exception e) {
            config.logger.error("Error getting access requests: {}", e.getMessage());
            return null;
        }
    }

    public BeaconEndpoint get(String endpointID) {
        try {
            return httpUtil.getModel(config.getAdminURI() + "/beacon/" + endpointID, BeaconEndpoint.class);
        } catch (Exception e) {
            config.logger.error("Error getting access request: {}", e.getMessage());
            return null;
        }
    }

    public boolean delete(String endpointID) {
        try {
            return httpUtil.deleteModel(config.getAdminURI() + "/beacon/" + endpointID);
        } catch (Exception e) {
            config.logger.error("Error deleting access request: {}", e.getMessage());
            return false;
        }
    }

    public boolean rotateKey(String endpointID) {
        try {
            httpUtil.post(config.getAdminURI() + "/beacon/" + endpointID + "/rotate_key", "");
        } catch (Exception e) {
            config.logger.error("Error rotating key: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public String getAuthKey(String endpointID) throws Exception {
        try {
            return httpUtil.get(config.getAdminURI() + "/beacon/" + endpointID + "/auth_key").body();
        } catch (Exception e) {
            config.logger.error("Error getting auth key: {}", e.getMessage());
            throw e;
        }
    }
}
