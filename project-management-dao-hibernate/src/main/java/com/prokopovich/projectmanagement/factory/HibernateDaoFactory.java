package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.dao.*;

import javax.persistence.EntityManagerFactory;

public class HibernateDaoFactory implements DaoFactory {

    private final EntityManagerFactory entityManagerFactory;

    public HibernateDaoFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public AccountActionDao getAccountActionDao() {
        return null;
    }

    @Override
    public AccountDao getAccountDao() {
        return null;
    }

    @Override
    public ActionDao getActionDao() {
        return null;
    }

    @Override
    public AttachmentDao getAttachmentDao() {
        return null;
    }

    @Override
    public CommentDao getCommentDao() {
        return null;
    }

    @Override
    public ProjectDao getProjectDao() {
        return null;
    }

    @Override
    public ProjectActionDao getProjectActionDao() {
        return null;
    }

    @Override
    public TaskDao getTaskDao() {
        return null;
    }

    @Override
    public TaskActionDao getTaskActionDao() {
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }
}
