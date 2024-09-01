package org.cafevariome;

import org.cafevariome.api.AccessRequestAPI;
import org.cafevariome.util.Authentication;
import org.cafevariome.util.ClientConfig;
import org.cafevariome.util.HttpUtil;

public class CafeVariomeClient {
    public final Authentication auth;

    public final AccessRequestAPI accessRequest;

    public CafeVariomeClient(ClientConfig config) {
        this.auth = new Authentication(config);
        HttpUtil httpUtil = new HttpUtil(this.auth, config);

        this.accessRequest = new AccessRequestAPI(config, httpUtil);
    }
}
