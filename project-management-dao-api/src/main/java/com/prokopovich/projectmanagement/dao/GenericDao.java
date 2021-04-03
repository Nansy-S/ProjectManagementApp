package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface GenericDao<T> {

    T create(T newObject, int id);

    T findOne(int id);

    Collection<T> findAll();

    Collection<T> findByParameter(String sql, String parameter);

    Collection<T> findByParameter(String sql, int parameter);
}
