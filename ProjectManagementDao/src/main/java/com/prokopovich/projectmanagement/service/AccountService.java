package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    boolean authorization(String login, String password);

    void addNewAccount(Account account);

    void editAccount(Account account);

    Optional<Account> getByAddressId(int id);

    List<Account> getByUserId(int id);
}
