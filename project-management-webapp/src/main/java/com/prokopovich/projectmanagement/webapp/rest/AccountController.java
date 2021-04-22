package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    private final ServiceFactory service = new ServiceFactoryImpl();
    private final UserService userService = service.getUserService();
    private final AccountService accountService = service.getAccountService();


    @GetMapping(value = "/users")
    public ResponseEntity<List<Account>> displayUsersByReporter() {
        LOGGER.trace("displayUsersByReporter method is executed");
        List<Account> userAccounts = accountService.getAllCreatedUser(
                35, AccountActionType.CREATE.getTitle());
        return new ResponseEntity<List<Account>>(userAccounts, HttpStatus.OK);
    }
}
