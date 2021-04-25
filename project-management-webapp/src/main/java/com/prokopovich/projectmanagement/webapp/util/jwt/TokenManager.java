package com.prokopovich.projectmanagement.webapp.util.jwt;

import com.prokopovich.projectmanagement.dao.AccountDao;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 7008375124389347049L;

    public static final long TOKEN_VALIDITY = 15 * 60 * 60;

    //private final AccountService accountService;
    private final AccountDao accountDao;

    @Value("${secret}")
    private String jwtSecret;

    @Autowired
    public TokenManager(AccountDao accountDao) {
        //this.accountService = accountService;
        this.accountDao = accountDao;
    }

    public String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            new Exception("Invalid token");
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        User user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public Account getCurrentUser() {
        String email = null;

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        if(authentication.getPrincipal() instanceof UserDetails) {
            UserDetails customUserDetails = (UserDetails) authentication.getPrincipal();
            email = customUserDetails.getUsername();
        } else {
            if(authentication.getPrincipal() instanceof String) {
                email = (String) authentication.getPrincipal();
            }
        }
        return accountDao.findAllByEmail(email).iterator().next();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
