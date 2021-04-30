package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.dao.*;
import com.prokopovich.projectmanagement.dao.hibernate.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateDaoFactory implements DaoFactory {

    private final EntityManagerFactory entityManagerFactory;

    public HibernateDaoFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("ps-hb-projectmanagement");
    }

    @Override
    public AccountActionDao getAccountActionDao() {
        return new AccountActionHibernateDao(entityManagerFactory);
    }

    @Override
    public AccountDao getAccountDao() {
        return new AccountHibernateDao(entityManagerFactory);
    }

    @Override
    public ActionDao getActionDao() {
        return new ActionHibernateDao(entityManagerFactory);
    }

    @Override
    public AttachmentDao getAttachmentDao() {
        return new AttachmentHibernateDao(entityManagerFactory);
    }

    @Override
    public CommentDao getCommentDao() {
        return new CommentHibernateDao(entityManagerFactory);
    }

    @Override
    public ProjectDao getProjectDao() {
        return new ProjectHibernateDao(entityManagerFactory, getProjectActionDao());
    }

    @Override
    public ProjectActionDao getProjectActionDao() {
        return new ProjectActionHibernateDao(entityManagerFactory);
    }

    @Override
    public TaskDao getTaskDao() {
        return new TaskHibernateDao(entityManagerFactory, getTaskActionDao());
    }

    @Override
    public TaskActionDao getTaskActionDao() {
        return new TaskActionHibernateDao(entityManagerFactory);
    }

    @Override
    public UserDao getUserDao() {
        return new UserHibernateDao(entityManagerFactory, getAccountActionDao());
    }
}
