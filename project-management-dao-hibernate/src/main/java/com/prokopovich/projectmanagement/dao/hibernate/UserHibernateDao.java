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
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserHibernateDao extends GenericHibernateDao<User> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserHibernateDao.class);

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
