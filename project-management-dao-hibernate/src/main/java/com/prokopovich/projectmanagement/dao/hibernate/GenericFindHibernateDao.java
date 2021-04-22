package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.GenericFindDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public abstract class GenericFindHibernateDao<T> extends BaseOperationHibernateDao<T> implements GenericFindDao<T> {

    private final EntityManagerFactory entityManagerFactory;

    private Class<T> className;

    public GenericFindHibernateDao(EntityManagerFactory entityManagerFactory, Class<T> className) {
        super(entityManagerFactory, className);
        this.entityManagerFactory = entityManagerFactory;
        this.className = className;
    }

    @Override
    public T findOne(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(className, id);
    }
}
