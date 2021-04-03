package com.prokopovich.projectmanagement.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.AccountAction;
import com.prokopovich.projectmanagement.service.AccountActionService;
import com.prokopovich.projectmanagement.service.AccountService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ActionController {

    private static final Logger LOGGER = LogManager.getLogger(ActionController.class);

    private final ServiceFactory service = new ServiceFactoryImpl();
    private final AccountActionService accountActionService = service.getAccountActionService();
    private final AccountService accountService = service.getAccountService();

    public void displayAccountActionByReporter() {
        List<AccountAction> accountActions;
        Account userAccount;
        int number = 0;

        LOGGER.trace("displayAccountActionByReporter method is executed");
        accountActions = accountActionService.findAllByReporter(App.getCurrentUser());
        System.out.println(" #) email - role - action type - time - reason");
        for (AccountAction action : accountActions) {
            userAccount = accountService.findByAccountId(action.getAccountId());
            number++;
            System.out.println(number + ") " +
                    userAccount.getEmail() + " - " +
                    userAccount.getRole() + " - " +
                    action.getAction().getType() + " - " +
                    action.getAction().getDatetime() + " - " +
                    action.getReason());
        }
    }
}
