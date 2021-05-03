package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryRestController {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountRestController.class);

    private final TokenManager tokenManager;
    private final AccountActionService accountActionService;
    private final AccountService accountService;

    @Autowired
    public HistoryRestController(AccountActionService accountActionService, AccountService accountService,
            TokenManager tokenManager) {
        this.accountActionService = accountActionService;
        this.accountService = accountService;
        this.tokenManager = tokenManager;
    }

    @GetMapping(value = "/account")
    public ResponseEntity<List<AccountAction>> displayAccountActionByReporter() {
        LOGGER.trace("displayAccountActionByReporter method is executed");
        List<AccountAction> accountActions = accountActionService.findAllByReporter(35); //////
        if(accountActions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountActions, HttpStatus.OK);
    }

    // displayAccountActionByUserId
}
