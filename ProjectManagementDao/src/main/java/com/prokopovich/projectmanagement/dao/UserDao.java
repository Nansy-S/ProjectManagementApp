package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.User;

import java.util.Collection;

public interface UserDao extends GenericDao<User> {

    boolean updateUser(int userId);

    Collection<User> findAllByCurrentStatus(String currentStatus);
}
