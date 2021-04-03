package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface GenericDaoWithHistory<T> extends GenericDao<T> {

    Collection<T> findAllByReporterAndAction(int reporterId, String actionType);
}
