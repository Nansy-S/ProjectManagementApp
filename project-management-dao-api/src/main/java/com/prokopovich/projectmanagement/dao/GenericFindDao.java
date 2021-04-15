package com.prokopovich.projectmanagement.dao;

public interface GenericFindDao<T> extends BaseOperationDao<T> {

    T findOne(int id);
}
