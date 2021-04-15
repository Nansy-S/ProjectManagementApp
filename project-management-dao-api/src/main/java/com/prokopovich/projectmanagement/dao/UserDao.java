package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.User;

import java.util.Collection;

public interface UserDao extends GenericFindDao<User> {

    boolean update(User user) throws DaoException;

    Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException;
}
