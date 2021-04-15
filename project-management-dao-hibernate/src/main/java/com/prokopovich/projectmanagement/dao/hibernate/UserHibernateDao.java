package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

public class UserHibernateDao extends GenericFindHibernateDao<User> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    public UserHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, User.class);
        this.entityManagerFactory = entityManagerFactory;
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
    public Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        return findByParameter("currentStatus", currentStatus);
    }
}
