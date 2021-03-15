package com.prokopovich;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.User;

public class App {
    public static void main( String[] args ) {
        DaoFactory mySQLDAOFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        UserDao userDAO = mySQLDAOFactory.getUserDAO();

        if (userDAO != null) {
            userDAO.findAll();
        }
        userDAO.findOne(2);

        User newUser = new User(3, "Developer", "Locked", "777");


    }
}
