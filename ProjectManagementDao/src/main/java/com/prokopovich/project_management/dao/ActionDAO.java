package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Action;

import java.util.Collection;

public interface ActionDAO {
    int createAction(Action action);
    Collection<Action> findAll();
    Collection<Action> findAllByReporterAction(int reporterAction);
    Collection<Action> findAllByTaskCodeProjectCode(int taskCode, String projectCode);
}
