package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;

import java.util.List;

public interface AccountActionService {

    void addNewAccountAction(AccountAction accountAction);

    List<AccountAction> findAllByReporter(Account reporter);
}
