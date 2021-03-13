package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.User;

import java.util.Collection;

public interface UserDao extends GenericDao<User> {

    Collection<User> findAllByCurrentStatus(String currentStatus) throws DaoException;
}
