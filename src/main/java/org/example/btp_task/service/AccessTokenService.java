package org.example.btp_task.service;

import org.example.btp_task.dto.AccessTokenDTO;
import org.example.btp_task.dto.RepositoryURLDTO;
import org.example.btp_task.repository.AccessTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    public AccessTokenService(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    public List<AccessTokenDTO> getAllAccessTokens() {
        return accessTokenRepository.findAll().stream().map(t ->
                        new AccessTokenDTO()
                                .setId(t.getId())
                                .setPlatform(t.getPlatform())
                                .setRepositories(t.getRepositoryURLList().stream()
                                        .map(r -> new RepositoryURLDTO()
                                                .setId(r.getId())
                                                .setUrl(r.getUrl()))
                                        .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void deleteAccessTokenById(long id) {
        accessTokenRepository.deleteById(id);
    }
}
