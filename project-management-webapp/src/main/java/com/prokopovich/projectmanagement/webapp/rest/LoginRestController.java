package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthorizationService;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtRequestModel;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtResponseModel;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginRestController {

    private final AuthorizationService authorizationService;
    private final TokenManager tokenManager;

    @Autowired
    public LoginRestController(AuthorizationService authorizationService, TokenManager tokenManager) {
        this.authorizationService = authorizationService;
        this.tokenManager = tokenManager;
    }

    @RequestMapping(value = "/api/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> login(@RequestBody JwtRequestModel loginRequest) {
        Account account = authorizationService.userAuthorization(loginRequest.getEmail(), loginRequest.getPassword());
        if(account != null) {
            String token = tokenManager.generateJwtToken(loginRequest.getEmail());
            Authentication authentication = tokenManager.getAuthentication(token);
            if (authentication != null) {
                return ResponseEntity.ok(new JwtResponseModel(
                        token,
                        account.getAccountId(),
                        account.getName(),
                        account.getEmail(),
                        account.getRole()
                ));
            }
        }
        return null;
    }
}
