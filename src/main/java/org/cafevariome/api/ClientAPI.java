package org.cafevariome.api;

import org.cafevariome.util.ClientConfig;
import org.cafevariome.util.HttpUtil;

public class ClientAPI {
    protected final ClientConfig config;
    protected final HttpUtil httpUtil;

    public ClientAPI(ClientConfig config, HttpUtil httpUtil) {
        this.config = config;
        this.httpUtil = httpUtil;
    }
}
