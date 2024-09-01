package org.cafevariome;

import org.cafevariome.util.Authentication;
import org.cafevariome.util.ClientConfig;
import org.cafevariome.util.HttpUtil;

public class CafeVariomeClient {
    private final ClientConfig config;
    private final Authentication auth;
    private final HttpUtil httpUtil;

    public CafeVariomeClient(ClientConfig config) {
        this.config = config;
        this.auth = new Authentication(this.config);
        this.httpUtil = new HttpUtil(this.auth, config);
    }
}
