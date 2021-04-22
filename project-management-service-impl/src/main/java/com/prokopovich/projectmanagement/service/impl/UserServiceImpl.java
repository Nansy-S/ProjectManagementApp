package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addNewUser(User user) {
        userDao.create(user);
    }

    @Override
    public boolean editUser(User user) {
        if (userDao.update(user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getByUserId(int id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    public List<User> getAllByCurrentStatus(String currentStatus) {
        return (List<User>) userDao.findAllByCurrentStatus(currentStatus);
    }
}
