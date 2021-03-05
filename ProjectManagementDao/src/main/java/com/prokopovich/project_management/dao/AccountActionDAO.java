package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.AccountAction;

import java.util.Collection;

public interface AccountActionDAO {
    int createAccountAction(AccountAction accountAction);
    Collection<AccountAction> findAll();
    Collection<AccountAction> findAllByAdminId(int adminId);
    Collection<AccountAction> findAllByUserId(int userId);
}
