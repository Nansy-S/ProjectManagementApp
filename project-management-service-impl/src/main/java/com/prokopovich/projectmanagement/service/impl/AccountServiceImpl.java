package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
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

    private final AccountDao accountDao;
    private final UserService userService;
    private final ActionService actionService;
    private final AccountActionService accountActionService;

    public AccountServiceImpl(AccountDao accountDao, UserService userService, ActionService actionService,
                              AccountActionService accountActionService) {
        this.accountDao = accountDao;
        this.userService = userService;
        this.actionService = actionService;
        this.accountActionService = accountActionService;
    }

    @Override
    public void addNewAccount(Account newAccount, Account reporter, User newUser, String reason) {
        int newAccountId = accountDao.create(newAccount);
        newAccount = accountDao.findOne(newAccountId);
        newUser.setUserId(newAccount.getAccountId());
        newUser.setCurrentStatus(AccountActionType.CREATE.getAccountStatus());
        userService.addNewUser(newUser);
        setAccountAction(newAccount.getAccountId(), reporter, reason, AccountActionType.CREATE.getTitle());
    }

    @Override
    public boolean editAccount(Account account, Account reporter, User user, String reason) {
        if (accountDao.update(account)) {
            if (userService.editUser(user)) {
                setAccountAction(account.getAccountId(), reporter, reason, AccountActionType.UPDATE.getTitle());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean changeRole(Account account, Account reporter, String reason) {
        if (accountDao.update(account)) {
            setAccountAction(account.getAccountId(), reporter, reason, AccountActionType.CHANGE_ROLE.getTitle());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changeStatus(User user, Account reporter, String reason, String typeAction) {
        if (userService.editUser(user)) {
            setAccountAction(user.getUserId(), reporter, reason, typeAction);
            return true;
        } else {
            return false;
        }
    }

    private void setAccountAction(int userId, Account reporter, String reason, String actionType) {
        Action newAction = new Action();
        AccountAction newAccountAction = new AccountAction();

        newAction.setType(actionType);
        newAction.setDatetime(LocalDateTime.now());
        newAction.setReporter(reporter.getAccountId());
        newAction.setReporterInfo(reporter);
        newAction = actionService.addNewAction(newAction);

        newAccountAction.setActionId(newAction.getActionId());
        newAccountAction.setAccountId(userId);
        newAccountAction.setReason(reason);
        newAccountAction.setAction(newAction);
        accountActionService.addNewAccountAction(newAccountAction);
    }

    @Override
    public List<Account> getAllCreatedUser(int reporterId, String actionType) {
        return (List<Account>) accountDao.findAllByReporterAndAction(reporterId, actionType);
    }

    @Override
    public Account findByAccountId(int id) {
        return accountDao.findOne(id);
    }

    @Override
    public List<Account> findByUserRole(String role) {
        return (List<Account>) accountDao.findAllByUserRole(role);
    }

    @Override
    public List<Account> findByUserFullName(String fullName) {
        return (List<Account>) accountDao.findAllByUserFullName(fullName);
    }
}
