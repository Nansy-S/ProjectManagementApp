package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Collection;

@Repository
public class UserHibernateDao extends GenericFindHibernateDao<User> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
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

    //@Override
    //public Collection<User> findAllByReporterAndAction(int reporterId, String actionType) {
    //    EntityManager entityManager = entityManagerFactory.createEntityManager();
    //    try {
    //        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    //        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    //        Root<User> userRoot = criteriaQuery.from(User.class);
    //        Join<User, AccountAction> joinAccountAction = userRoot.join("accountId");
//
//
//
    //        Predicate predicateReporter = criteriaBuilder.equal(accountRoot.get("reporter"), reporterId);
    //        Predicate predicateActionType = criteriaBuilder.equal(tRoot.get("type"), actionType);
    //        criteriaQuery.where(predicateReporter, predicateActionType);
    //        return entityManager.createQuery(criteriaQuery).getResultList();
    //    } finally {
    //        entityManager.close();
    //    }
    //}
}
