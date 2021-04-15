package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface GenericDaoWithHistory<T> extends GenericFindDao<T> {

    Collection<T> findAllByReporterAndAction(int reporterId, String actionType);
}
