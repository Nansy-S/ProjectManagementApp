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

    @Autowired
    private AccountDao accountDao;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountDao.findAllByEmail(email).iterator().next();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(account);
    }
}
