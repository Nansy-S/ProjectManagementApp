package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;

import java.util.Collection;

public interface AccountDao extends GenericDaoWithHistory<Account> {

    boolean update(Account account) throws DaoException;

    Collection<Account> findAllByEmail(String email) throws DaoException;

    Collection<Account> findAllByUserRole(String role) throws DaoException;

    Account findByEmail(String email) throws DaoException;
}
