package org.cafevariome;

import org.cafevariome.api.AccessRequestAPI;
import org.cafevariome.util.AppConfig;
import org.cafevariome.util.Authentication;
import org.cafevariome.util.HttpUtil;

public class CafeVariomeClient {
    public final Authentication auth;

    public final AccessRequestAPI accessRequest;

    public CafeVariomeClient(AppConfig config) {
        this.auth = new Authentication(config);
        HttpUtil httpUtil = new HttpUtil(this.auth, config);

        this.accessRequest = new AccessRequestAPI(config, httpUtil);
    }
}
