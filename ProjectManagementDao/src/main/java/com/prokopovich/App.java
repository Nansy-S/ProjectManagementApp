package com.prokopovich;

import com.prokopovich.project_management.dao.UserMySQLDAO;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        UserMySQLDAO userDAO = new UserMySQLDAO();
        System.out.println(userDAO.findAll());
    }
}
