package com.prokopovich.projectmanagement.webapp.util.jwt;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.webapp.util.jwt.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountDao accountDao;

    @Autowired
    public CustomUserDetailsService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountDao.findByEmail(email);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(account);
    }
}
