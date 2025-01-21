package org.example.btp_task.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tokens")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String platform;

    @OneToMany(mappedBy = "accessToken", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RepositoryURL> repositoryURLList;


    public AccessToken() {
        this.repositoryURLList = new ArrayList<RepositoryURL>();
    }

    public Long getId() {
        return id;
    }

    public AccessToken setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AccessToken setToken(String token) {
        this.token = token;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public AccessToken setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public List<RepositoryURL> getRepositoryURLList() {
        return repositoryURLList;
    }

    public AccessToken setRepositoryURLList(List<RepositoryURL> repositoryURLList) {
        this.repositoryURLList = repositoryURLList;
        return this;
    }
}
