package org.cafevariome.api;

import org.cafevariome.util.AppConfig;
import org.cafevariome.util.HttpUtil;

public class ClientAPI {
    protected final AppConfig config;
    protected final HttpUtil httpUtil;

    public ClientAPI(AppConfig config, HttpUtil httpUtil) {
        this.config = config;
        this.httpUtil = httpUtil;
    }
}
