package org.cafevariome;

import org.cafevariome.util.AppConfig;

public class Main {
    public static void main(String[] args) {
        AppConfig config = new AppConfig.ClientConfigBuilder("www.cafevariome.org/demo-v3")
                .setRedirectURI("https://www.cafevariome.org/demo-v3/callback.html").build();

        CafeVariomeClient client = new CafeVariomeClient(config);
        client.auth().login("demo", "demo");
    }
}
