package org.example.btp_task.dto;

public class URLTokenRequest {
    private String url;
    private String accessToken;

    public String getUrl() {
        return url;
    }

    public URLTokenRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public URLTokenRequest setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
