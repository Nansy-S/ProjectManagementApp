package com.prokopovich;

import com.prokopovich.projectmanagement.dao.UserDao;
import com.prokopovich.projectmanagement.factory.DaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main( String[] args ) {
        DaoFactory mySQLDAOFactory = DaoFactory.getDAOFactory(DaoFactory.MYSQL);
        UserDao userDAO = mySQLDAOFactory.getUserDAO();
        if(userDAO != null) {
            userDAO.findAll();
        }
        userDAO.findOne(2);
    }
}
