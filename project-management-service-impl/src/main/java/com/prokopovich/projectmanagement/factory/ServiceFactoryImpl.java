package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.enumeration.DatabaseType;
import com.prokopovich.projectmanagement.service.*;
import com.prokopovich.projectmanagement.service.impl.*;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactoryImpl extends ServiceFactoryProvider {

    private final DaoFactory daoFactory;

    public ServiceFactoryImpl() {
        daoFactory = DaoFactoryProvider.getDAOFactory(DatabaseType.HIBERNATE);
    }

    @Override
    public AuthenticationService getAuthenticationService() {
        return new AuthenticationServiceImpl(daoFactory.getAccountDao());
    }

    @Override
    public AccountActionService getAccountActionService() {
        return new AccountActionServiceImpl(daoFactory.getAccountActionDao());
    }

    @Override
    public ActionService getActionService() {
        return new ActionServiceImpl(daoFactory.getActionDao());
    }

    @Override
    public AccountServiceImpl getAccountService() {
        return new AccountServiceImpl(daoFactory.getAccountDao(), getUserService(),
                getActionService(), getAccountActionService());
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl(daoFactory.getUserDao());
    }

    @Override
    public AttachmentService getAttachmentService() {
        return new AttachmentServiceImpl(daoFactory.getAttachmentDao());
    }

    @Override
    public CommentService getCommentService() {
        return new CommentServiceImpl(daoFactory.getCommentDao());
    }

    @Override
    public ProjectService getProjectService() {
        return new ProjectServiceImpl(daoFactory.getProjectDao(), getActionService(), getProjectActionService());
    }

    @Override
    public ProjectActionService getProjectActionService() {
        return new ProjectActionServiceImpl(daoFactory.getProjectActionDao());
    }

    @Override
    public TaskService getTaskService() {
        return new TaskServiceImpl(daoFactory.getTaskDao(), getActionService(), getTaskActionService());
    }

    @Override
    public TaskActionService getTaskActionService() {
        return new TaskActionServiceImpl(daoFactory.getTaskActionDao());
    }
}
