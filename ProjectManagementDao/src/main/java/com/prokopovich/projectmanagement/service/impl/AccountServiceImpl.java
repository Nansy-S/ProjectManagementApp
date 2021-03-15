package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {



    @Override
    public boolean authorization(String login, String password) {
        return false;
    }

    @Override
    public void addNewAccount(Account account) {

    }

    @Override
    public void editAccount(Account account) {

    }

    @Override
    public Optional<Account> getByAddressId(int id) {
        return Optional.empty();
    }

    @Override
    public List<Account> getByUserId(int id) {
        return null;
    }
}
