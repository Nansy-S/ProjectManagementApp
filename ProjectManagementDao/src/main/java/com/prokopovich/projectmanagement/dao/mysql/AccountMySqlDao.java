package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

public class AccountMySqlDao extends GenericMySqlDao<Account> implements AccountDao {

    @Override
    public String getSqlSelectAll() {
        return null;
    }

    @Override
    public String getSqlSelectOne() {
        return null;
    }

    @Override
    protected List<Account> parseResultSet(ResultSet rs) {
        return null;
    }

    @Override
    public Account create(Account newObject) throws DaoException {
        return null;
    }

    @Override
    public boolean updateAccount(int accountId) {
        return false;
    }

    @Override
    public Collection<Account> findAllByUserRole(String role) {
        return null;
    }

    @Override
    public Collection<Account> findAllByUserFullName(String surname, String name, String patronymic) {
        return null;
    }
}
