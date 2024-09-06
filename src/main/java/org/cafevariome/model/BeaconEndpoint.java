package org.cafevariome.model;

import org.cafevariome.enums.BeaconAuthMethod;

public class BeaconEndpoint extends AppModel{
    private String endpointID;
    private String endpointPath;
    private String networkID;
    private UserMapping mapping;
    private BeaconAuthMethod beaconAuthMethod;
    private String beaconAuthKey;

    public BeaconEndpoint() {
    }

    public BeaconEndpoint(String endpointID,
                          String endpointPath,
                          String networkID,
                          UserMapping mapping,
                          BeaconAuthMethod beaconAuthMethod) {
        this.endpointID = endpointID;
        this.endpointPath = endpointPath;
        this.networkID = networkID;
        this.mapping = mapping;
        this.beaconAuthMethod = beaconAuthMethod;
    }

    public BeaconEndpoint(String endpointID,
                          String endpointPath,
                          String networkID,
                          UserMapping mapping,
                          BeaconAuthMethod beaconAuthMethod,
                          String beaconAuthKey) {
        this.endpointID = endpointID;
        this.endpointPath = endpointPath;
        this.networkID = networkID;
        this.mapping = mapping;
        this.beaconAuthMethod = beaconAuthMethod;
        this.beaconAuthKey = beaconAuthKey;
    }

    public String getEndpointID() {
        return endpointID;
    }

    public void setEndpointID(String endpointID) {
        this.endpointID = endpointID;
    }

    public String getEndpointPath() {
        return endpointPath;
    }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public UserMapping getMapping() {
        return mapping;
    }

    public void setMapping(UserMapping mapping) {
        this.mapping = mapping;
    }

    public BeaconAuthMethod getBeaconAuthMethod() {
        return beaconAuthMethod;
    }

    public void setBeaconAuthMethod(BeaconAuthMethod beaconAuthMethod) {
        this.beaconAuthMethod = beaconAuthMethod;
    }

    public String getBeaconAuthKey() {
        return beaconAuthKey;
    }

    public void setBeaconAuthKey(String beaconAuthKey) {
        this.beaconAuthKey = beaconAuthKey;
    }
}
