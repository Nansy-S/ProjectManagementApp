package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ActionMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactoryProvider;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.service.ActionService;

public class ActionServiceImpl implements ActionService {

    private static final ActionMySqlDao ACTION_DAO =
            (ActionMySqlDao) DaoFactoryProvider.getDAOFactory(1).getActionDao();

    @Override
    public Action addNewAction(Action action) {
        action = ACTION_DAO.create(action, action.getActionId());
        return action;
    }


}
