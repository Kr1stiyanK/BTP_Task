package org.example.btp_task.service;

import org.example.btp_task.dto.URLTokenRequest;
import org.example.btp_task.entities.AccessToken;
import org.example.btp_task.entities.RepositoryURL;
import org.example.btp_task.repository.AccessTokenRepository;
import org.example.btp_task.repository.URLRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class URLTokenService {

    private static String currentPlatform;

    private static final String GITHUB_URL = "https://api.github.com/user";
    private static final String BITBUCKET_URL = "https://api.bitbucket.org/2.0/repositories";
    private static final String GITLAB_URL = "https://gitlab.com/api/v4/user";

    private final URLRepository urlRepository;
    private final AccessTokenRepository accessTokenRepository;
    private final HttpClient httpClient;

    public URLTokenService(URLRepository urlRepository, AccessTokenRepository accessTokenRepository) {
        this.urlRepository = urlRepository;
        this.accessTokenRepository = accessTokenRepository;
        this.httpClient = HttpClient.newHttpClient();
    }

    public static String getCurrentPlatform() {
        return currentPlatform;
    }

    public static void setCurrentPlatform(String currentPlatform) {
        URLTokenService.currentPlatform = currentPlatform;
    }

    public boolean validateTokenAndURL(URLTokenRequest urlTokenRequest) {
        return validateToken(identifyURLPlatform(urlTokenRequest.getUrl()), urlTokenRequest.getAccessToken());
    }

    public void saveRepositoryURLWithToken(URLTokenRequest urlTokenRequest, String platform) {
        AccessToken token = this.accessTokenRepository.findByToken(urlTokenRequest.getAccessToken()).stream().findFirst().orElseGet(
                () -> {
                    AccessToken newToken = new AccessToken()
                            .setToken(urlTokenRequest.getAccessToken())
                            .setPlatform(platform);
                    return this.accessTokenRepository.save(newToken);
                });

        RepositoryURL repositoryURL = new RepositoryURL()
                .setUrl(urlTokenRequest.getUrl())
                .setAccessToken(token);

        this.urlRepository.save(repositoryURL);
    }

    public String identifyURLPlatform(String repositoryURL) {
        if (repositoryURL.contains("github.com")) {
            setCurrentPlatform("github.com");
            return GITHUB_URL;
        } else if (repositoryURL.contains("bitbucket.org")) {
            setCurrentPlatform("bitbucket.org");
            return BITBUCKET_URL;
        } else if (repositoryURL.contains("gitlab.com")) {
            setCurrentPlatform("gitlab.com");
            return GITLAB_URL;
        }
        setCurrentPlatform(null);
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
