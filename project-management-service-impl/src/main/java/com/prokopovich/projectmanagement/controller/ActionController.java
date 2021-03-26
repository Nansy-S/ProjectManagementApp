package com.prokopovich.projectmanagement.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.ActionService;
import com.prokopovich.projectmanagement.service.impl.AccountActionServiceImpl;
import com.prokopovich.projectmanagement.service.impl.ActionServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ActionController {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private static ServiceFactory service = new ServiceFactoryImpl();
    private static ActionService actionService = new ActionServiceImpl();
    private static AccountActionService accountActionService = new AccountActionServiceImpl();

    public void displayAccountActionByReporter() {


    }

}
