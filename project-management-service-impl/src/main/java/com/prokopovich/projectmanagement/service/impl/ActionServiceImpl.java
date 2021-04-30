package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.ActionDao;
import com.prokopovich.projectmanagement.model.Action;
import com.prokopovich.projectmanagement.service.ActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActionServiceImpl implements ActionService {

    private final ActionDao actionDao;

    public ActionServiceImpl(ActionDao actionDao) {
        this.actionDao = actionDao;
    }

    @Override
    public Action addNewAction(Action action) {
        action = actionDao.create(action);
        return actionDao.findOne(action.getActionId());
    }
}
