package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;

import java.util.Collection;
import java.util.List;

public interface AccountActionDao extends GenericDao<AccountAction> {

    Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException;

    Collection<AccountAction> findAllByReporter(Account reporter) throws DaoException;

    List<Integer> findUserIdByReporterAndAction(int reporterId, String action) throws DaoException;
}
