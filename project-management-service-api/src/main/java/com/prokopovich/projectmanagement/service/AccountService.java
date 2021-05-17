package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    User addNewAccount(User newUser, int reporterId);

    boolean editAccount(User newUser, int reporterId);

    List<Account> getAllCreatedUser(int reporterId, String actionType);

    boolean changeRole(Account account, int reporterId, String reason);

    boolean changeStatus(User user, int reporterId, String reason);

    Account findByAccountId(int id);

    List<Account> findByEmail(String email);

    List<Account> findByUserRole(String role);

}
