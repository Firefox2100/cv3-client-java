package org.cafevariome;

import org.cafevariome.api.AccessRequestAPI;
import org.cafevariome.api.BeaconEndpointAPI;
import org.cafevariome.api.DataFileAPI;
import org.cafevariome.util.AppConfig;
import org.cafevariome.util.Authentication;
import org.cafevariome.util.HttpUtil;

public class CafeVariomeClient {
    private final Authentication authentication;

    private final AccessRequestAPI accessRequestAPI;
    private final BeaconEndpointAPI beaconEndpointAPI;
    private final DataFileAPI dataFileAPI;

    public CafeVariomeClient(AppConfig config) {
        this.authentication = new Authentication(config);
        HttpUtil httpUtil = new HttpUtil(this.authentication, config);

        this.accessRequestAPI = new AccessRequestAPI(config, httpUtil);
        this.beaconEndpointAPI = new BeaconEndpointAPI(config, httpUtil);
        this.dataFileAPI = new DataFileAPI(config, httpUtil);
    }

    public Authentication auth() {
        return authentication;
    }

    public AccessRequestAPI accessRequest() {
        return accessRequestAPI;
    }

    public BeaconEndpointAPI beaconEndpoint() {
        return beaconEndpointAPI;
    }

    public DataFileAPI dataFile() {
        return dataFileAPI;
    }
}
