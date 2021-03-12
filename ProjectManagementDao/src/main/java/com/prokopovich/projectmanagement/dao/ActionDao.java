package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Action;

import java.util.Collection;

public interface ActionDao extends GenericDao<Action> {

    Collection<Action> findAllByReporter(int reporter);

    Collection<Action> findAllByType(String type);
}
