package com.prokopovich.projectmanagement.factory;

import com.prokopovich.project.management.dao.*;
import com.prokopovich.project_management.dao.*;
import com.prokopovich.projectmanagement.dao.*;

public abstract class DaoFactory {

    public static final int MYSQL = 1;

    public abstract AccountActionDao getAccountActionDAO();

    public abstract AccountDao getAccountDAO();

    public abstract ActionDao getActionDAO();

    public abstract AttachmentDao getAttachmentDAO();

    public abstract CommentDao getCommentDAO();

    public abstract ProjectDao getProjectDAO();

    public abstract TaskDao getTaskDAO();

    public abstract UserDao getUserDAO();


    public static DaoFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MYSQL:
                return new MySqlDaoFactory();
            default:
                return null;
        }
    }
}
