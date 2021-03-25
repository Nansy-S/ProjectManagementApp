package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    void addNewAccount(Account newAccount, User newUser, String reason);

    boolean editAccount(Account account, User user, String reason);

    boolean changeRole(Account account, String reason);

    boolean changeStatus(User user, String reason, String typeAction);

    Account getByAccountId(int id);

    List<Account> getAllByUserRole(String role);

    List<Account> getAllByUserFullName(String fullName);

    List<Account> getAllByReporterAndAction(int reporterId, String action);
}
