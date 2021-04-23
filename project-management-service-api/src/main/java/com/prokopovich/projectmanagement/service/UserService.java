package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.User;

import java.util.List;

public interface UserService {

    User addNewUser(User user);

    boolean editUser(User user);

    User getByUserId(int id);

    List<User> getAll();

    List<User> getAllByCurrentStatus(String currentStatus);
}
