package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.*;

public interface ServiceFactory {

    AuthenticationService getAuthenticationService();

    AccountActionService getAccountActionService();

    ActionService getActionService();

    AccountService getAccountService();

    UserService getUserService();

    AttachmentService getAttachmentService();

    CommentService getCommentService();

    ProjectService getProjectService();

    ProjectActionService getProjectActionService();

    TaskService getTaskService();

    TaskActionService getTaskActionService();
}
