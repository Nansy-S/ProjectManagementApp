package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Account;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class AccountHibernateDao extends GenericHibernateDaoWithHistory<Account> implements AccountDao {

    private static final Logger LOGGER = LogManager.getLogger(AccountHibernateDao.class);
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT e FROM Account e WHERE e.accountId IN " +
            "(SELECT aa.accountId FROM AccountAction aa INNER JOIN Action a ON a.actionId = aa.actionId ";

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AccountHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Account.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    public String getSqlSelectByReporterAndAction() {
        return SQL_SELECT_BY_REPORTER_AND_ACTION;
    }

    @Override
    public boolean update(Account account) throws DaoException {
        LOGGER.trace("updateAccount method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(account);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Account> findAllByEmail(String email) throws DaoException {
        LOGGER.trace("findAllByEmail method is executed - email = " + email);
        return findByParameter("email", email);
    }

    @Override
    public Collection<Account> findAllByUserRole(String role) throws DaoException {
        LOGGER.trace("findAllByUserRole method is executed - role = " + role);
        return findByParameter("role", role);
    }

    @Override
    public Collection<Account> findAllByUserFullName(String fullName) throws DaoException {
        LOGGER.trace("findAllByUserFullName method is executed - Full name = " + fullName);
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //try {
        //    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //    CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        //    Root<Account> tRoot = criteriaQuery.from(Account.class);
        //    //Predicate predicateName = criteriaBuilder.equal(tRoot.get("name"), name);
        //    //Predicate predicateFirstname = criteriaBuilder.equal(tRoot.get("surname"), surname);
        //    //Predicate predicatePatronymic = criteriaBuilder.equal(tRoot.get("patronymic"), patronymic);
        //    //criteriaQuery.where(predicateName, predicateFirstname, predicatePatronymic);
        //    return entityManager.createQuery(criteriaQuery).getResultList();
        //} finally {
        //    entityManager.close();
        //}
//
        //return findByParameter("SQL_SELECT_BY_FULL_NAME", fullName);
        return null;
    }

    @Override
    public Account findByEmail(String email) throws DaoException {
        LOGGER.trace("findAllByEmail method is executed - email = " + email);
        List<Account> accounts = (List<Account>) findByParameter("email", email);
        return accounts.iterator().next();
    }
}
