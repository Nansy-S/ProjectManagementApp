package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthenticationService;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtResponseModel;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationRestController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    @Autowired
    public AuthorizationRestController(AuthenticationService authenticationService,
                                       AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> authorization(@RequestBody Account request) {
        Account account = authenticationService.userAuthorization(request.getEmail(), request.getPassword());
        String token = tokenManager.generateJwtToken(account.getEmail());
        return ResponseEntity.ok(new JwtResponseModel(token));
    }
}
