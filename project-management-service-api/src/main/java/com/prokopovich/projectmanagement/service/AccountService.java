package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    Account authorization(String login, String password);

    Account addNewAccount(Account newAccount, User newUser, Action newAction, AccountAction newAccountAction);

    void editAccount(Account account);

    Account getByAccountId(int id);

    List<Account> getAll();

    List<Account> getAllByUserRole(String role);

    List<Account> getAllByUserFullName(String fullName);
}
