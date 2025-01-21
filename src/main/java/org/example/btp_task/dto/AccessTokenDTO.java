package org.example.btp_task.dto;

import java.util.List;

public class AccessTokenDTO {
    private long id;
    private String platform;
    private List<RepositoryURLDTO> repositories;

    public long getId() {
        return id;
    }

    public AccessTokenDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public AccessTokenDTO setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public List<RepositoryURLDTO> getRepositories() {
        return repositories;
    }

    public AccessTokenDTO setRepositories(List<RepositoryURLDTO> repositories) {
        this.repositories = repositories;
        return this;
    }
}
