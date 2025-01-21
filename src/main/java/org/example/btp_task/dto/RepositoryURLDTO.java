package org.example.btp_task.dto;

public class RepositoryURLDTO {
    private long id;
    private String url;
    private String tokenPlatform;

    public long getId() {
        return id;
    }

    public RepositoryURLDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RepositoryURLDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTokenPlatform() {
        return tokenPlatform;
    }

    public RepositoryURLDTO setTokenPlatform(String tokenPlatform) {
        this.tokenPlatform = tokenPlatform;
        return this;
    }
}
