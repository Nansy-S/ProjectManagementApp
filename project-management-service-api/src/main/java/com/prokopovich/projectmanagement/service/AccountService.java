package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface AccountService {

    Account authorization(String login, String password);

    Account addNewAccount(Account account, User user);

    void editAccount(Account account);

    Account getByAccountId(int id);

    List<Account> getAll();

    List<Account> getAllByUserRole(String role);

    List<Account> getAllByUserFullName(String fullName);
}
