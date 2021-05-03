package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.AccountStatus;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.UserService;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAccountRestController {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountRestController.class);

    private final UserService userService;
    private final AccountService accountService;
    private final TokenManager tokenManager;

    @Autowired
    public UserAccountRestController(UserService userService, AccountService accountService, TokenManager tokenManager) {
        this.userService = userService;
        this.accountService = accountService;
        this.tokenManager = tokenManager;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Account>> getUsersByReporter() {
        LOGGER.trace("getUsersByReporter method is executed");
        List<Account> userAccounts = accountService.getAllCreatedUser(
                tokenManager.getCurrentUser().getAccountId(), AccountActionType.CREATE.getTitle());
        if(userAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAccounts, HttpStatus.OK);
    }

    @GetMapping(value = "/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        LOGGER.trace("getUsersByRole method is executed");
        List<User> userList = userService.getAllByUserRole(role);
        if(userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable int id) {
        LOGGER.trace("getUserInfo method is executed");
        User user = userService.getByUserId(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> addUser(@RequestBody Account newAccount,
                                        @RequestBody User newUser,
                                        @RequestBody String reason) {
        User addedUser = accountService.addNewAccount(newAccount,
                tokenManager.getCurrentUser().getAccountId(), newUser, reason);
        LOGGER.trace("new user added - " + addedUser.toString());
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Boolean> editUser(@PathVariable int id,
                                        @RequestBody Account newAccount,
                                        @RequestBody User newUser,
                                        @RequestBody String reason) {
        LOGGER.trace("new user added");
        if(accountService.editAccount(newAccount,
                tokenManager.getCurrentUser().getAccountId(), newUser, reason)) {
            LOGGER.trace("user information successfully edited - new user information - " + newUser.toString());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/edit/status/{id}/{accountStatus}")
    public ResponseEntity<Boolean> changeUserStatus(@PathVariable int id,
                                                    @PathVariable AccountStatus accountStatus,
                                                    @RequestBody User user,
                                                    @RequestBody String reason) {
        if(accountService.changeStatus(user, tokenManager.getCurrentUser().getAccountId(), reason)) {
            LOGGER.trace("user status successfully edited - new status - " + user.getCurrentStatus());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/edit/role/{id}")
    public ResponseEntity<Boolean> changeUserRole(@PathVariable int id,
                                                  @RequestBody Account account,
                                                  @RequestBody String reason) {
        if(accountService.changeRole(account, tokenManager.getCurrentUser().getAccountId(), reason)) {
            LOGGER.trace("user role successfully edited - new role - " + account.getRole());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}