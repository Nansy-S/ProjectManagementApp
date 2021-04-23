package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.GenericDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public abstract class GenericHibernateDao<T> implements GenericDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AccountActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    private Class<T> className;

    public GenericHibernateDao(EntityManagerFactory entityManagerFactory, Class<T> className) {
        this.entityManagerFactory = entityManagerFactory;
        this.className = className;
    }

    @Override
    public T create(T newObject) {
        LOGGER.trace("create object method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newObject);
            entityManager.getTransaction().commit();
            entityManager.close();
            return newObject;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public T findOne(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(className, id);
    }
    
    @Override
    public Collection<T> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select e from " + className.getSimpleName() + " e")
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<T> findByParameter(String field, int parameter) {
        return findByOneParameter(field, parameter);
    }

    @Override
    public Collection<T> findByParameter(String field, String parameter) {
        return findByOneParameter(field, parameter);
    }

    private <G> Collection<T> findByOneParameter(String field, G parameter) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(className);
            Root<T> tRoot = criteriaQuery.from(className);
            Predicate predicate = criteriaBuilder.equal(tRoot.get(field), parameter);
            criteriaQuery.where(predicate);
            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
