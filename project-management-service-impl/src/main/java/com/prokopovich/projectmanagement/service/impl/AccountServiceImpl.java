package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.dao.AccountMySqlDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private static final AccountMySqlDao ACCOUNT_DAO = (AccountMySqlDao) DaoFactory.getDAOFactory(1).getAccountDao();
    private static final UserService USER_SERVICE = ServiceFactory.getServiceFactory(1).getUserServiceImpl();
    private static final ActionService ACTION_SERVICE = ServiceFactory.getServiceFactory(1).getActionServiceImpl();
    private static final AccountActionService ACCOUNT_ACTION_SERVICE =
            ServiceFactory.getServiceFactory(1).getAccountActionServiceImpl();

    @Override
    public Account addNewAccount(Account newAccount, User newUser, String reason) {
        newAccount = ACCOUNT_DAO.create(newAccount);

        newUser.setUserId(newAccount.getAccountId());
        newUser.setCurrentStatus(AccountActionType.CREATE.getAccountStatus());
        USER_SERVICE.addNewUser(newUser);
        setAccountAction(newAccount, reason, AccountActionType.CREATE.getTitle());
        return newAccount;
    }

    @Override
    public boolean editAccount(Account account, User user, String reason) {
        if (ACCOUNT_DAO.update(account)) {
            if (USER_SERVICE.editUser(user)) {
                setAccountAction(account, reason, AccountActionType.UPDATE.getTitle());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void setAccountAction(Account account, String reason, String actionType) {
        Action newAction = new Action();
        AccountAction newAccountAction = new AccountAction();

        newAction.setType(actionType);
        newAction.setDatetime(LocalDateTime.now());
        newAction.setReporter(App.getCurrentUser().getAccountId());
        newAction.setReporterInfo(App.getCurrentUser());
        newAction = ACTION_SERVICE.addNewAction(newAction);

        newAccountAction.setActionId(newAction.getActionId());
        newAccountAction.setAccountId(account.getAccountId());
        newAccountAction.setReason(reason);
        newAccountAction.setAction(newAction);
        ACCOUNT_ACTION_SERVICE.addNewAccountAction(newAccountAction);
    }

    @Override
    public Account getByAccountId(int id) {
        return ACCOUNT_DAO.findOne(id);
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) ACCOUNT_DAO.findAll();
    }

    @Override
    public List<Account> getAllByUserRole(String role) {
        return (List<Account>) ACCOUNT_DAO.findAllByUserRole(role);
    }

    @Override
    public List<Account> getAllByUserFullName(String fullName) {
        return (List<Account>) ACCOUNT_DAO.findAllByUserFullName(fullName);
    }

    @Override
    public List<Account> getAllByReporterAndAction(int reporterId, String action) {
        return (List<Account>) ACCOUNT_DAO.findAllByReporterAndAction(reporterId, action);
    }
}
