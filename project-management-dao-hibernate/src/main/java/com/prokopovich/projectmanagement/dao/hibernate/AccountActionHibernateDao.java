package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.AccountActionDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.AccountAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;

public class AccountActionHibernateDao extends BaseOperationHibernateDao<AccountAction> implements AccountActionDao {

    private static final Logger LOGGER = LogManager.getLogger(AccountActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    public AccountActionHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, AccountAction.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Collection<AccountAction> findAllByAccountId(int accountId) throws DaoException {
        LOGGER.trace("findAllByAccountId method is executed - accountId = " + accountId);
        return findByParameter("accountId", accountId);
    }

    @Override
    public Collection<AccountAction> findAllByReporter(int reporterId)
                throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporterID = " + reporterId);
        return findByParameter("reporter", reporterId);
    }
}
