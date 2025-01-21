package org.example.btp_task.service;

import org.example.btp_task.dto.URLTokenRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class URLTokenService {

    private static final String GITHUB_URL = "https://api.github.com/user";
    private static final String BITBUCKET_URL = "https://api.bitbucket.org/2.0/user";
    private static final String GITLAB_URL = "https://gitlab.com/api/v4/user";

    private final HttpClient httpClient;

    public URLTokenService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public boolean validateTokenAndURL(URLTokenRequest urlTokenRequest) {
        return validateToken(identifyURLPlatform(urlTokenRequest.getUrl()), urlTokenRequest.getAccessToken());
    }


    public String identifyURLPlatform(String repositoryURL) {
        if (repositoryURL.contains("github.com")) {
            return GITHUB_URL;
        } else if (repositoryURL.contains("bitbucket.org")) {
            return BITBUCKET_URL;
        } else if (repositoryURL.contains("gitlab.com")) {
            return GITLAB_URL;
        }
        return "Unknown platform";
    }


    private boolean validateToken(String url, String token) {
        if (!url.equals("Unknown platform")) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Authorization", "Bearer " + token)
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                return response.statusCode() == 200;

            } catch (IOException | InterruptedException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
