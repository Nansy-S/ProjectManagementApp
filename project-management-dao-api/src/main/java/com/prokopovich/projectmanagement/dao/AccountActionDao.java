package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;

import java.util.Collection;

public interface AccountActionDao extends BaseOperationDao<AccountAction> {

    Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException;

    Collection<AccountAction> findAllByReporter(int reporterId) throws DaoException;
}
