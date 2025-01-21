package org.example.btp_task.service;

import org.example.btp_task.dto.RepositoryURLDTO;
import org.example.btp_task.repository.URLRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryURLService {

    private final URLRepository urlRepository;

    public RepositoryURLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<RepositoryURLDTO> getAllRepositoryURLs() {
        return urlRepository.findAll().stream().map(e -> new RepositoryURLDTO()
                .setUrl(e.getUrl())
                .setId(e.getId())
                .setTokenPlatform(e.getAccessToken().getPlatform())).collect(Collectors.toList());
    }

    public void deleteRepositoryURLById(long id) {
        urlRepository.deleteById(id);
    }
}
