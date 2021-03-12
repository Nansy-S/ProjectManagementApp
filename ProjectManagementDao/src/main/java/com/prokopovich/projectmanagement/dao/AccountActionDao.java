package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.AccountAction;

import java.util.Collection;

public interface AccountActionDao extends GenericDao<AccountAction> {

    Collection<AccountAction> findAllByAccountId(int accountId);
}
