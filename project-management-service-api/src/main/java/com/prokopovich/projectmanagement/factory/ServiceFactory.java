package com.prokopovich.projectmanagement.factory;

import com.prokopovich.projectmanagement.service.*;

public interface ServiceFactory {

    AuthenticationService getAuthenticationServiceImpl();

    AccountActionService getAccountActionServiceImpl();

    ActionService getActionServiceImpl();

    AccountService getAccountServiceImpl();

    UserService getUserServiceImpl();
}
