package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.GenericDao;

import java.sql.Connection;

public abstract class AbstractMySqlDao<T> implements GenericDao<T> {
    private Connection connection;

}
