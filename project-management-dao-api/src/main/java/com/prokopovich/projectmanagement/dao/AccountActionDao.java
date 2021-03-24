package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface AccountActionDao extends GenericDao<AccountAction> {

    Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException;

    List<Integer> findAllByReporterAndAction(int reporterId, String action) throws DaoException;
}
