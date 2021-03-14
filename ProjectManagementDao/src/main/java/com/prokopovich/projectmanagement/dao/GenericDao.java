package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;

import java.util.Collection;

public interface GenericDao<T> {

    T create(T newObject) throws DaoException;

    T findOne(int id) throws DaoException;

    Collection<T> findAll() throws DaoException;

    Collection<T> findByParameter(String sql, String parameter) throws DaoException;

    Collection<T> findByParameter(String sql, int parameter) throws DaoException;
}
