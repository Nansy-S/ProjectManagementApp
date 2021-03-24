package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    Account addNewAccount(Account newAccount, User newUser, AccountAction newAccountAction);

    boolean editAccount(Account account, User user, AccountAction newAccountAction);

    Account getByAccountId(int id);

    List<Account> getAll();

    List<Account> getAllByUserRole(String role);

    List<Account> getAllByUserFullName(String fullName);

    List<Account> getAllByReporterAndAction(int reporterId, String action);
}
