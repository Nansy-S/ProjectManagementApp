package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface BaseOperationDao<T> {

    int create(T newObject);

    Collection<T> findAll();

    Collection<T> findByParameter(String query, int parameter);

    Collection<T> findByParameter(String query, String parameter);
}
