package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.dao.*;

public abstract class DaoFactory {

    public static final int MYSQL = 1;

    public abstract AccountActionDao getAccountActionDao();

    public abstract AccountDao getAccountDao();

    public abstract ActionDao getActionDao();

    public abstract AttachmentDao getAttachmentDao();

    public abstract CommentDao getCommentDao();

    public abstract ProjectDao getProjectDao();

    public abstract TaskDao getTaskDao();

    public abstract UserDao getUserDao();

    public static DaoFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDaoFactory();
            default:
                return null;
        }
    }
}
