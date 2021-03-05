package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.User;

import java.util.Collection;

public interface UserDAO {
    int createUser(User user);
    boolean updateUser(int userId);
    User findUser(int userId);
    Collection<User> findAll();
    Collection<User> findAllByCurrentStatus(String currentStatus);
    Collection<User> findAllByTeamId(int teamId);
}
