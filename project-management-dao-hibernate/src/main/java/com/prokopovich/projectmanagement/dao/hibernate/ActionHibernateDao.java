package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.ActionDao;
import com.prokopovich.projectmanagement.model.Action;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;

public class ActionHibernateDao extends GenericFindHibernateDao<Action> implements ActionDao {

    private static final Logger LOGGER = LogManager.getLogger(ActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    public ActionHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Action.class);
        this.entityManagerFactory = entityManagerFactory;
    }

}
