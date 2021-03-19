package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ActionMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.service.ActionService;

public class ActionServiceImpl implements ActionService {

    private static final ActionMySqlDao ACTION_DAO =
            (ActionMySqlDao) DaoFactory.getDAOFactory(1).getActionDao();

    @Override
    public Action addNewAction(Action action) {
        action = ACTION_DAO.create(action);
        return action;
    }
}
