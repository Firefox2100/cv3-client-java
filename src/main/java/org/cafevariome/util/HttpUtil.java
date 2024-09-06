package org.cafevariome.util;

import org.cafevariome.model.AppModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtil {
    private final Authentication auth;
    private final AppConfig config;
    private final HttpClient httpClient;

    public HttpUtil(Authentication auth, AppConfig config) {
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

    public <T> T getModel(String path, Class<T> clazz) {
        try {
            HttpResponse<String> response = get(path);
            return AppModel.fromJson(response.body(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error getting model from " + path + ": " + e.getMessage(), e);
        }
    }

    public <T> List<T> getModels(String path, Class<T> clazz) {
        try {
            HttpResponse<String> response = get(path);
            return AppModel.fromJsonList(response.body(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error getting models from " + path + ": " + e.getMessage(), e);
        }
    }

    public HttpResponse<String> post(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean postModel(String path, AppModel model) {
        try {
            String jsonString = model.toJson();
            HttpResponse<String> response = post(path, jsonString);

            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new RuntimeException("Response body:" + response.body());
            }
        } catch (Exception e) {
            config.logger.error("Error posting model: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public HttpResponse<String> patch(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean patchModel(String path, AppModel model) {
        try {
            String jsonString = model.toJson();
            HttpResponse<String> response = patch(path, jsonString);

            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new RuntimeException("Response body:" + response.body());
            }
        } catch (Exception e) {
            config.logger.error("Error patching model: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public HttpResponse<String> put(String path, String jsonBody) throws Exception {
        HttpRequest request = createRequest(path)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean putModel(String path, AppModel model) {
        try {
            String jsonString = model.toJson();
            HttpResponse<String> response = put(path, jsonString);

            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new RuntimeException("Response body:" + response.body());
            }
        } catch (Exception e) {
            config.logger.error("Error putting model: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public HttpResponse<String> delete(String path) throws Exception {
        HttpRequest request = createRequest(path)
                .DELETE()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean deleteModel(String path) {
        try {
            HttpResponse<String> response = delete(path);

            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new RuntimeException("Response body:" + response.body());
            }
        } catch (Exception e) {
            config.logger.error("Error deleting model: {}", e.getMessage());
            return false;
        }

        return true;
    }
}
