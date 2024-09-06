package org.cafevariome;

import org.cafevariome.api.AccessRequestAPI;
import org.cafevariome.api.BeaconEndpointAPI;
import org.cafevariome.util.AppConfig;
import org.cafevariome.util.Authentication;
import org.cafevariome.util.HttpUtil;

public class CafeVariomeClient {
    public final Authentication auth;

    private final AccessRequestAPI accessRequestAPI;
    private final BeaconEndpointAPI beaconEndpointAPI;

    public CafeVariomeClient(AppConfig config) {
        this.auth = new Authentication(config);
        HttpUtil httpUtil = new HttpUtil(this.auth, config);

        this.accessRequestAPI = new AccessRequestAPI(config, httpUtil);
        this.beaconEndpointAPI = new BeaconEndpointAPI(config, httpUtil);
    }

    public AccessRequestAPI accessRequest() {
        return accessRequestAPI;
    }

    public BeaconEndpointAPI beaconEndpoint() {
        return beaconEndpointAPI;
    }
}
