package org.example.btp_task.controller;

import jakarta.servlet.http.HttpSession;
import org.example.btp_task.dto.AccessTokenDTO;
import org.example.btp_task.dto.RepositoryURLDTO;
import org.example.btp_task.dto.URLTokenRequest;
import org.example.btp_task.service.AccessTokenService;
import org.example.btp_task.service.RepositoryURLService;
import org.example.btp_task.service.URLTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class WebController {

    private final URLTokenService tokenService;
    private final RepositoryURLService repositoryURLService;
    private final AccessTokenService accessTokenService;

    public WebController(URLTokenService tokenService, RepositoryURLService repositoryURLService, AccessTokenService accessTokenService) {
        this.tokenService = tokenService;
        this.repositoryURLService = repositoryURLService;
        this.accessTokenService = accessTokenService;
    }


    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateRepositoryURLandAccessToken(@RequestBody URLTokenRequest urlTokenRequest, HttpSession session) {
        String URLPlatform = tokenService.identifyURLPlatform(urlTokenRequest.getUrl());
        boolean isValid = tokenService.validateTokenAndURL(urlTokenRequest);
        if (isValid) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(400).body(false);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRepositoryAndToken(@RequestBody URLTokenRequest urlTokenRequest, HttpSession session) {
        try {
            String platform = URLTokenService.getCurrentPlatform();
            if (platform == null) {
                throw new IllegalStateException("Platform not identified. Validate token first.");
            }

            tokenService.saveRepositoryURLWithToken(urlTokenRequest, platform);
            return ResponseEntity.status(HttpStatus.CREATED).body("Repository URL and Access Token successfully saved");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save Repository URL and Access Token: " + e.getMessage());
        }
    }

    @GetMapping("/repositories")
    public ResponseEntity<List<RepositoryURLDTO>> getAllRepositoryURLs() {
        List<RepositoryURLDTO> allRepositoryURLs = repositoryURLService.getAllRepositoryURLs();
        return ResponseEntity.ok(allRepositoryURLs);
    }

    @DeleteMapping("/repositories/{id}")
    public ResponseEntity<Void> deleteRepositoryURLById(@PathVariable("id") Long id) {
        this.repositoryURLService.deleteRepositoryURLById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/access-tokens")
    public ResponseEntity<List<AccessTokenDTO>> getAllAccessTokens() {
        List<AccessTokenDTO> allAccessTokens = accessTokenService.getAllAccessTokens();
        return ResponseEntity.ok(allAccessTokens);
    }

    @DeleteMapping("/access-tokens/{id}")
    public ResponseEntity<Void> deleteAllAccessTokens(@PathVariable("id") long id) {
        accessTokenService.deleteAccessTokenById(id);
        return ResponseEntity.noContent().build();
    }
}
