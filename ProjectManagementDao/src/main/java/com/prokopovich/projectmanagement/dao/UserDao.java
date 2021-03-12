package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.User;

import java.util.Collection;

public interface UserDao {

    int createUser(User user);

    boolean updateUser(int userId);

    Collection<User> findAllByCurrentStatus(String currentStatus);
}
