package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;

import java.util.Collection;

public interface AccountDao extends GenericDao<Account> {

    boolean update(Account account) throws DaoException;

    Collection<Account> findAllByUserRole(String role) throws DaoException;

    Collection<Account> findAllByUserFullName(String fullName) throws DaoException;

    Collection<Account> findAllByEmailAndPassword(String email, String password) throws DaoException;
}
