package com.prokopovich.projectmanagement.dao;

import java.util.Collection;

public interface GenericDao<T> {

    T create(T newInstance);

    T find(Object id);

    Collection<T> findAll();
}
