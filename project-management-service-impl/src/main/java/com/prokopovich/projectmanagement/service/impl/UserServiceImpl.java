package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.UserMySqlDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserMySqlDao USER_DAO = (UserMySqlDao) DaoFactory.getDAOFactory(1).getUserDao();

    @Override
    public void addNewUser(User user) {
        USER_DAO.create(user);
    }

    @Override
    public void editAccount(User user) {
        USER_DAO.update(user);
    }

    @Override
    public User getByUserId(int id) {
        return USER_DAO.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) USER_DAO.findAll();
    }

    @Override
    public List<User> getAllByCurrentStatus(String currentStatus) {
        return (List<User>) USER_DAO.findAllByCurrentStatus(currentStatus);
    }
}
