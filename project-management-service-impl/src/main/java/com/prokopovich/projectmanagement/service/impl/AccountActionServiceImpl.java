package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountActionDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.service.AccountActionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountActionServiceImpl implements AccountActionService {

    private final AccountActionDao accountActionDao;

    public AccountActionServiceImpl(AccountActionDao accountActionDao) {
        this.accountActionDao = accountActionDao;
    }

    @Override
    public void addNewAccountAction(AccountAction accountAction) {
        accountActionDao.create(accountAction);
    }

    @Override
    public List<AccountAction> findAllByReporter(int reporterId) {
        return (List<AccountAction>) accountActionDao.findAllByReporter(reporterId);
    }
}
