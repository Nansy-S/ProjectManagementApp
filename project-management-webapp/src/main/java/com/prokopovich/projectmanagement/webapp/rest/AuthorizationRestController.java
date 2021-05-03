package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AuthenticationService;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtRequestModel;
import com.prokopovich.projectmanagement.webapp.util.jwt.JwtResponseModel;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorizationRestController {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationRestController.class);
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

    @RequestMapping(value = "/api/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> authorization(@RequestBody JwtRequestModel loginRequest) {
        Account account = authenticationService.userAuthorization(loginRequest.getEmail(), loginRequest.getPassword());
        String token = tokenManager.generateJwtToken(loginRequest.getEmail());
        return ResponseEntity.ok(new JwtResponseModel(token, account.getAccountId(),
                account.getSurname() + " " + account.getName() + " " + account.getPatronymic(),
                account.getEmail(),
                account.getRole()));
    }
}
