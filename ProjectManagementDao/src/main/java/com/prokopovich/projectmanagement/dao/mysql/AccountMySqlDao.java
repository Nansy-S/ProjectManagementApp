package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountMySqlDao extends GenericMySqlDao<Account> implements AccountDao {

    private static final String SQL_SELECT_ALL = "SELECT * FROM accounts";
    private static final String SQL_SELECT_ONE = "SELECT * FROM accounts WHERE account_id = ?";
    private static final String SQL_CREATE = "INSERT INTO accounts (account_id, name, surname, patronymic, email, " +
            "password, role, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static Logger logger = LogManager.getLogger(AccountMySqlDao.class);

    @Override
    public String getSqlSelectAll() {
        return SQL_SELECT_ALL;
    }

    @Override
    public String getSqlSelectOne() {
        return SQL_SELECT_ONE;
    }

    @Override
    protected List<Account> parseResultSet(ResultSet rs) {
        List<Account> accounts = new ArrayList<Account>();
    //    Account accountBean;
    //    try {
    //        while (rs.next()) {
    //            accountBean = new Account();
    //            accountBean.setAccountId(rs.getInt(1));
    //            accountBean.setName(rs.getString(2));
    //            accountBean.setSurname(rs.getString(3));
    //            accountBean.setPatronymic(rs.getString(4));
    //            accountBean.setEmail(rs.getString(5));
    //            accountBean.setPassword(rs.getString(6));
    //            accountBean.setRole(rs.getString(7));
    //            accountBean.getPhoto(rs.getBlob(8));
    //            accounts.add(accountBean);
    //            logger.debug("User.userId:" + userBean.getUserId() +
    //                    " User.position:" + userBean.getPosition() +
    //                    " User.currentStatus:" + userBean.getCurrentStatus() +
    //                    " User.phone:" + userBean.getPhone());
    //        }
    //    } catch (SQLException ex) {
    //        logger.error(ex.getMessage());
    //    }
        return accounts;
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
