package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.dao.*;

public interface DaoFactory {

    AccountActionDao getAccountActionDao();

    AccountDao getAccountDao();

    ActionDao getActionDao();

    AttachmentDao getAttachmentDao();

    CommentDao getCommentDao();

    ProjectDao getProjectDao();

    ProjectActionDao getProjectActionDao();

    TaskDao getTaskDao();

    TaskActionDao getTaskActionDao();

    UserDao getUserDao();
}
