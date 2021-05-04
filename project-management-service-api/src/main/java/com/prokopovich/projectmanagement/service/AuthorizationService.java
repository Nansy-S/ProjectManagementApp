package com.prokopovich.projectmanagement.service;

import com.prokopovich.projectmanagement.model.Account;

public interface AuthorizationService {

    Account userAuthorization(String login, String password);
}
