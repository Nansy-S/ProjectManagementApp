package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.mysql.AccountMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private static final AccountMySqlDao ACCOUNT_DAO = (AccountMySqlDao) DaoFactory.getDAOFactory(1).getAccountDAO();

    @Override
    public Account authorization(String login, String password) {
        Account account = (Account) ACCOUNT_DAO.findAllByEmailAndPassword(login, password);
        return account;
    }

    @Override
    public void addNewAccount(Account account) {
        ACCOUNT_DAO.create(account);
    }

    @Override
    public void editAccount(Account account) {
        ACCOUNT_DAO.update(account);
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
}
