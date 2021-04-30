package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.AccountStatus;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final UserService userService;
    private final ActionService actionService;
    private final AccountActionService accountActionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountDao accountDao, UserService userService, ActionService actionService,
                              AccountActionService accountActionService) {
        this.accountDao = accountDao;
        this.userService = userService;
        this.actionService = actionService;
        this.accountActionService = accountActionService;
    }

    @Override
    public User addNewAccount(Account newAccount, int reporterId, User newUser, String reason) {
        //newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        newAccount = accountDao.create(newAccount);
        newAccount = accountDao.findOne(newAccount.getAccountId());
        newUser.setUserId(newAccount.getAccountId());
        newUser.setCurrentStatus(AccountActionType.CREATE.getAccountStatus());
        newUser = userService.addNewUser(newUser);
        setAccountAction(newUser.getUserId(), reporterId, reason, AccountActionType.CREATE.getTitle());
        return newUser;
    }

    @Override
    public boolean editAccount(Account account, int reporterId, User user, String reason) {
        if (accountDao.update(account)) {
            if (userService.editUser(user)) {
                setAccountAction(account.getAccountId(), reporterId, reason, AccountActionType.UPDATE.getTitle());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean changeRole(Account account, int reporterId, String reason) {
        if (accountDao.update(account)) {
            setAccountAction(account.getAccountId(), reporterId, reason, AccountActionType.CHANGE_ROLE.getTitle());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changeStatus(User user, int reporterId, String reason) {
        if (userService.editUser(user)) {
            AccountStatus status = AccountStatus.getByTitle(user.getCurrentStatus());
            String typeAction = status.getAccountActionType("typeAction");
            setAccountAction(user.getUserId(), reporterId, reason, typeAction);
            return true;
        } else {
            return false;
        }
    }

    private void setAccountAction(int userId, int reporterId, String reason, String actionType) {
        Action newAction = new Action();
        AccountAction newAccountAction = new AccountAction();

        newAction.setType(actionType);
        newAction.setDatetime(LocalDateTime.now());
        newAction.setReporter(reporterId);
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
    public List<Account> findByEmail(String email) {
        return (List<Account>) accountDao.findAllByEmail(email);
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
