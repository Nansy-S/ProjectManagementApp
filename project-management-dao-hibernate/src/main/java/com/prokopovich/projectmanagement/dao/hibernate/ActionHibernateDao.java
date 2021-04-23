package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.ActionDao;
import com.prokopovich.projectmanagement.model.Action;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class ActionHibernateDao extends GenericHibernateDao<Action> implements ActionDao {

    private static final Logger LOGGER = LogManager.getLogger(ActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ActionHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Action.class);
        this.entityManagerFactory = entityManagerFactory;
    }
}
