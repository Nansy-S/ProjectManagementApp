package com.prokopovich;

import com.prokopovich.project_management.dao.UserDAOImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main( String[] args ) {
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.findAll().toString());
        logger.debug(userDAO);
    }
}
