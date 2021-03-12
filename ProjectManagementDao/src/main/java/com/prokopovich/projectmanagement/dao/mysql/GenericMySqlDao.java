package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.GenericDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class GenericMySqlDao<T> implements GenericDao<T> {

    protected abstract List<T> parseResultSet(ResultSet rs);

    public abstract String getSqlSelectAll();

    public abstract String getSqlSelectOne();

    @Override
    public T findOne(Object id) throws DaoException {
        List<T> objects;
        String sql = getSqlSelectOne();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer)id);
            ResultSet rs = statement.executeQuery();
            objects = parseResultSet(rs);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        if (objects == null || objects.size() == 0) {
            return null;
        }
        if (objects.size() > 1) {
            throw new DaoException("Received more than one record.");
        }
        return objects.iterator().next();
    }

    @Override
    public Collection<T> findAll() throws DaoException {
        List<T> objects;
        String sql = getSqlSelectAll();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            objects = parseResultSet(rs);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return objects;
    }
}
