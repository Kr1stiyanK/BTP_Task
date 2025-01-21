package org.example.btp_task.controller;

import org.example.btp_task.dto.URLTokenRequest;
import org.example.btp_task.service.URLTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class WebController {

    private final URLTokenService tokenService;

    public WebController(URLTokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateRepositoryURLandAccessToken(@RequestBody URLTokenRequest urlTokenRequest) {
        boolean isValid = tokenService.validateTokenAndURL(urlTokenRequest);
        return ResponseEntity.ok(isValid);
    }
}
