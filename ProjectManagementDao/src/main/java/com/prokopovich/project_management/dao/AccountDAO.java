package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Account;

import java.util.Collection;

public interface AccountDAO {
    int createAccount(Account account);
    boolean updateAccount(int accountId);
    Account findAccount(int accountId);
    Collection<Account> findAll();
    Collection<Account> findAllByUserRole(String role);
}
