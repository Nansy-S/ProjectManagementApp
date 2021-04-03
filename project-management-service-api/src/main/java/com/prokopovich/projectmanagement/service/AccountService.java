package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    void addNewAccount(Account newAccount, Account reporter, User newUser, String reason);

    boolean editAccount(Account account, Account reporter, User user, String reason);

    List<Account> getAllCreatedUser(int reporterId, String actionType);

    boolean changeRole(Account account, Account reporter, String reason);

    boolean changeStatus(User user, Account reporter, String reason, String typeAction);

    Account findByAccountId(int id);

    List<Account> findByUserRole(String role);

    List<Account> findByUserFullName(String fullName);

}
