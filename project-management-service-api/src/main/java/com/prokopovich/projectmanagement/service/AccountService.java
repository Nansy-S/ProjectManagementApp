package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    void addNewAccount(Account newAccount, Account reporter, User newUser, String reason);

    boolean editAccount(Account account, Account reporter, User user, String reason);

    boolean changeRole(Account account, Account reporter, String reason);

    boolean changeStatus(User user, Account reporter, String reason, String typeAction);

    Account getByAccountId(int id);

    List<Account> getAllByUserRole(String role);

    List<Account> getAllByUserFullName(String fullName);

    List<Account> getAllByReporterAndAction(Account reporter, String actionType);
}
