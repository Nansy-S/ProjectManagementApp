package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;

public interface AuthenticationService {

    Account userAuthorization(String login, String password);
}
