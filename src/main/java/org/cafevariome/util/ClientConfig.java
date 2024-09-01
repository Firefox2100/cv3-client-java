package org.cafevariome.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConfig {
    public String host;
    public int port;
    public String protocol;
    public Logger logger;
    public String redirectURI;

    public ClientConfig(String host) {
        this.host = host;
        this.port = 443;
        this.protocol = "https";
        this.logger = LoggerFactory.getLogger("CafeVariomeClient");
        this.redirectURI = "http://localhost:49430";
    }

    public ClientConfig(ClientConfigBuilder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.protocol = builder.protocol;
        this.logger = builder.logger;
        this.redirectURI = builder.redirectURI;
    }

    public String getURI() {
        return protocol + "://" + host + ":" + port;
    }

    public String getAdminURI() {
        return this.getURI() + "/api";
    }

    public static class ClientConfigBuilder {
        private final String host;
        private int port = 443;
        private String protocol = "https";
        private Logger logger = LoggerFactory.getLogger("CafeVariomeClient");
        private String redirectURI = "http://localhost:49430";

        public ClientConfigBuilder(String host) {
            this.host = host;
        }

        public ClientConfigBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public ClientConfigBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ClientConfigBuilder setLogger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public ClientConfigBuilder setRedirectURI(String redirectURI) {
            this.redirectURI = redirectURI;
            return this;
        }

        public ClientConfig build() {
            return new ClientConfig(this);
        }
    }
}
