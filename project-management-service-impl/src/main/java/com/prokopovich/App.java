package com.prokopovich;

import com.prokopovich.projectmanagement.model.*;

public class App {

    private static Account currentUser = new Account();

    public static void main( String[] args ) {
    }

    public static Account getCurrentUser(){
        return currentUser;
    }

}
