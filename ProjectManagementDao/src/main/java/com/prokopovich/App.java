package com.prokopovich;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import com.prokopovich.projectmanagement.model.User;

import java.sql.SQLException;

public class App {
    public static void main( String[] args ) {
        DaoFactory mySQLDAOFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        UserDao userDAO = mySQLDAOFactory.getUserDAO();
        try {
            if (userDAO != null) {
                userDAO.findAll();
            }
            userDAO.findOne(2);
        }
        catch (SQLException e){

        }
        User newUser = new User(3, "Developer", "Locked", "777");


    }
}
