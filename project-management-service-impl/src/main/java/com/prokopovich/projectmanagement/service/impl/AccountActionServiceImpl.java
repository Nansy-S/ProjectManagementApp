package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountActionMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.service.AccountActionService;

import java.util.List;

public class AccountActionServiceImpl implements AccountActionService {

    private static final AccountActionMySqlDao ACCOUNT_ACTION_DAO =
            (AccountActionMySqlDao) DaoFactoryProvider.getDAOFactory(1).getAccountActionDao();

    @Override
    public void addNewAccountAction(AccountAction accountAction) {
        ACCOUNT_ACTION_DAO.create(accountAction, accountAction.getActionId());
    }

    @Override
    public List<AccountAction> findAllByReporter(Account reporter) {
        List<AccountAction> accountActions = (List<AccountAction>) ACCOUNT_ACTION_DAO.findAllByReporter(reporter);
        return accountActions;
    }
}
