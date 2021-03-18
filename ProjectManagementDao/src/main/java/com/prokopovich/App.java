package com.prokopovich;

import com.prokopovich.projectmanagement.controller.AdminController;

public class App {
    public static void main( String[] args ) {
        AdminController controller = new AdminController();
        controller.addUser();
        controller.findAccount();
    }
}
