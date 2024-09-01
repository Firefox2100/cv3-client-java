package org.cafevariome.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Authentication {
    private final ClientConfig config;
    private String accessToken;
    private String refreshToken;

    public Authentication(ClientConfig config) {
        this.config = config;
    }

    // Helper method to parse query parameters from a URI
    private static Map<String, String> getQueryMap(String query) {
        Map<String, String> map = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                map.put(entry[0], entry[1]);
            } else {
                map.put(entry[0], "");
            }
        }
        return map;
    }

    public boolean refreshToken() {
        HttpClient client = HttpClient.newHttpClient();

        // Prepare the POST request data to refresh the access token
        Map<String, String> postData = new HashMap<>();
        postData.put("grant_type", "refresh_token");
        postData.put("refresh_token", this.refreshToken);

        String form = postData.entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        // Send the POST request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(config.getAdminURI() + "/auth/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the JSON response to extract the access token and refresh token
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> jsonResponse = mapper.readValue(response.body(), Map.class);

                this.accessToken = (String) jsonResponse.get("access_token");
                this.refreshToken = (String) jsonResponse.get("refresh_token");

                this.config.logger.info("Successfully refreshed access token and refresh token.");
                return true;
            } else {
                this.config.logger.error("Failed to refresh access token: unexpected status code {}", response.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            this.config.logger.error("Failed to refresh access token: {}", e.getMessage());
            return false;
        }
    }

    public boolean login(String userName, String password) {
        HttpClient client = HttpClient.newHttpClient();

        // Load the Keycloak configuration from the server
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(config.getAdminURI() + "/config/keycloak"))
                .GET()
                .build();

        KeycloakConfig keycloakConfig;

        try {
            HttpResponse<String> configResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            keycloakConfig = mapper.readValue(configResponse.body(), KeycloakConfig.class);
        } catch (IOException | InterruptedException e) {
            this.config.logger.error("Failed to load Keycloak configuration from server: {}", e.getMessage());
            return false;
        }

        // Build the Keycloak authorization URI
        String keycloakAuthURI = String.format("%s/realms/%s/protocol/openid-connect/auth?client_id=%s&response_type=code&redirect_uri=%s&scope=openid%%20profile%%20email",
                keycloakConfig.url,
                keycloakConfig.realm,
                keycloakConfig.clientID,
                URLEncoder.encode(keycloakConfig.redirectURI, StandardCharsets.UTF_8));

        String authorizationCode;

        // Get the authorization code
        try {
            // Get the login page
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(keycloakAuthURI))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            // Parse the login page to find the form action URL
            Document loginPage = Jsoup.parse(getResponse.body());
            Element formElement = loginPage.selectFirst("form[method=post]");
            String postURI = formElement.attr("action").replaceAll("amp;", "");

            // Wait for a moment before sending the POST request
            Thread.sleep(500);

            // Prepare the POST request data
            Map<String, String> postData = new HashMap<>();
            postData.put("username", userName);
            postData.put("password", password);
            postData.put("credentialId", "");

            String form = postData.entrySet()
                    .stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            // Send the POST request
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(postURI))
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            if (postResponse.statusCode() == 302) {
                // Extract the authorization code from the redirect URI
                String redirectionURI = postResponse.headers().firstValue("location").orElse("");
                URI uri = URI.create(redirectionURI);
                authorizationCode = getQueryMap(uri.getQuery()).get("code");
            } else {
                this.config.logger.error("Authentication failed: unexpected status code {}", postResponse.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            this.config.logger.error("Failed to authenticate: {}", e.getMessage());
            return false;
        }

        // Exchange the authorization code for an access token with backend server
        try {
            // Prepare the POST request data to exchange the authorization code
            Map<String, String> postData = new HashMap<>();
            postData.put("grant_type", "authorization_code");
            postData.put("redirect_uri", config.redirectURI);
            postData.put("code", authorizationCode);

            String form = postData.entrySet()
                    .stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            // Send the POST request
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(config.getAdminURI() + "/auth/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the JSON response to extract the access token and refresh token
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> jsonResponse = mapper.readValue(response.body(), Map.class);

                this.accessToken = (String) jsonResponse.get("access_token");
                this.refreshToken = (String) jsonResponse.get("refresh_token");

                this.config.logger.info("Successfully obtained access token and refresh token.");
                return true;
            } else {
                this.config.logger.error("Failed to exchange authorization code: unexpected status code {}", response.statusCode());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            this.config.logger.error("Failed to exchange authorization code: {}", e.getMessage());
            return false;
        }
    }

    public boolean login(String refreshToken) {
        this.refreshToken = refreshToken;
        return refreshToken();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    private static class KeycloakConfig {
        private String url;
        private String realm;
        private String clientID;
        private String redirectURI;
    }
}
