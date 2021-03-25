package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountActionMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.service.AccountActionService;

public class AccountActionServiceImpl implements AccountActionService {

    private static final AccountActionMySqlDao ACCOUNT_ACTION_DAO =
            (AccountActionMySqlDao) DaoFactory.getDAOFactory(1).getAccountActionDao();

    @Override
    public void addNewAccountAction(AccountAction accountAction) {
        ACCOUNT_ACTION_DAO.create(accountAction, accountAction.getActionId());
    }
}
