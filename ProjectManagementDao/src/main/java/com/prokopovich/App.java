package com.prokopovich;

import com.prokopovich.project_management.dao.UserDAOImpl;

public class App {
    public static void main( String[] args ) {
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.findAll().toString());
    }
}
