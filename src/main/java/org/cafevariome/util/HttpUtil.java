package org.cafevariome.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {
    private final Authentication auth;
    private final ClientConfig config;
    private final HttpClient httpClient;

    public HttpUtil(Authentication auth, ClientConfig config) {
        this.auth = auth;
        this.config = config;

        this.httpClient = HttpClient.newHttpClient();
    }

    private HttpRequest.Builder createRequest(String path) {
        return HttpRequest.newBuilder()
                .uri(URI.create(config.getURI() + path))
                .header("Authorization", "Bearer " + auth.getAccessToken())
                .header("Content-Type", "application/json");
    }

    public HttpResponse<String> get(String path) throws Exception {
        HttpRequest request = createRequest(path)
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> patch(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> put(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(String path) throws Exception {
        HttpRequest request = createRequest(path)
                .DELETE()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
