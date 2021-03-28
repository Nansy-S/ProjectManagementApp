package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.*;
import com.prokopovich.projectmanagement.service.impl.*;

public class ServiceFactoryImpl extends ServiceFactoryProvider {

    @Override
    public AuthenticationServiceImpl getAuthenticationServiceImpl() {
        return new AuthenticationServiceImpl();
    }

    @Override
    public AccountActionServiceImpl getAccountActionServiceImpl() {
        return new AccountActionServiceImpl();
    }

    @Override
    public ActionServiceImpl getActionServiceImpl() {
        return new ActionServiceImpl();
    }

    @Override
    public AccountServiceImpl getAccountServiceImpl() {
        return new AccountServiceImpl();
    }

    @Override
    public UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl();
    }

    @Override
    public AttachmentService getAttachmentService() {
        return new AttachmentServiceImpl();
    }

    @Override
    public CommentService getCommentService() {
        return new CommentServiceImpl();
    }

    @Override
    public ProjectService getProjectService() {
        return new ProjectServiceImpl();
    }

    @Override
    public ProjectActionService getProjectActionService() {
        return new ProjectActionServiceImpl();
    }

    @Override
    public TaskService getTaskService() {
        return new TaskServiceImpl();
    }

    @Override
    public TaskActionService getTaskActionService() {
        return new TaskActionServiceImpl();
    }
}
