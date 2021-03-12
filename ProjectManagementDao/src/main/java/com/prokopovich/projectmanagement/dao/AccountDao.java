package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Account;

import java.util.Collection;

public interface AccountDao extends GenericDao<Account> {

    boolean updateAccount(int accountId);

    Collection<Account> findAllByUserRole(String role);

    Collection<Account> findAllByUserFullName(String surname, String name, String patronymic);
}
