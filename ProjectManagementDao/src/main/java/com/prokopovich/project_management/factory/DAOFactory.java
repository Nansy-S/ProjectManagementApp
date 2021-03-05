package com.prokopovich.project_management.factory;

import com.prokopovich.project_management.dao.*;

public abstract class DAOFactory {
    public abstract AccountActionDAO getAccountActionDAO();
    public abstract AccountDAO getAccountDAO();
    public abstract ActionDAO getActionDAO();
    public abstract AttachmentDAO getAttachmentDAO();
    public abstract CommentDAO getCommentDAO();
    public abstract ProjectDAO getProjectDAO();
    public abstract TaskDAO getTaskDAO();
    public abstract TeamDAO getTeamDAO();
    public abstract UserDAO getUserDAO();

    public static MySQLDAOFactory getDAOFactory() {
          return new MySQLDAOFactory();
    }
}
