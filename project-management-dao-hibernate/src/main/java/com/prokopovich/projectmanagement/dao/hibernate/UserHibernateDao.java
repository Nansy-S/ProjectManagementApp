package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.AccountActionDao;
import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public class UserHibernateDao extends GenericHibernateDao<User> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserHibernateDao.class);
    private static final String SQL_SELECT_BY_ROLE = "SELECT e FROM User e " +
            "INNER JOIN Account a ON e.userId = a.accountId ";

    private final EntityManagerFactory entityManagerFactory;
    private final AccountActionDao accountActionDao;

    @Autowired
    public UserHibernateDao(EntityManagerFactory entityManagerFactory, AccountActionDao accountActionDao) {
        super(entityManagerFactory, User.class);
        this.entityManagerFactory = entityManagerFactory;
        this.accountActionDao = accountActionDao;
    }

    @Override
    public boolean update(User user) throws DaoException {
        LOGGER.trace("update user method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<User> findAllByUserRole(String role) throws DaoException {
        LOGGER.trace("findAllByUserRole method is executed - role = " + role);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = SQL_SELECT_BY_ROLE + " WHERE a.role =: role";
            Query query = entityManager.createQuery(sql);
            query.setParameter("role", role);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        List<User> userList = (List<User>) findByParameter("currentStatus", currentStatus);
        userList = setListActions(userList);
        return userList;
    }

    private List<User> setListActions(List<User> userList) {
        for(User user : userList) {
            user.setAccountActions(
                    (List<AccountAction>) accountActionDao.findAllByAccountId(user.getUserId()));
        }
        return userList;
    }
}
