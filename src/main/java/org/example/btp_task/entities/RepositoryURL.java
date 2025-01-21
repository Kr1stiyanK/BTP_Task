package org.example.btp_task.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "repositories")
public class RepositoryURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccessToken accessToken;


    public Long getId() {
        return id;
    }

    public RepositoryURL setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RepositoryURL setUrl(String url) {
        this.url = url;
        return this;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public RepositoryURL setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
