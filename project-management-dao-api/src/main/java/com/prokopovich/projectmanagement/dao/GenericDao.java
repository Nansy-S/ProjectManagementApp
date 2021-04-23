package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface GenericDao<T> {

    T create(T newObject);

    T findOne(int id);

    Collection<T> findAll();

    Collection<T> findByParameter(String query, int parameter);

    Collection<T> findByParameter(String query, String parameter);
}
