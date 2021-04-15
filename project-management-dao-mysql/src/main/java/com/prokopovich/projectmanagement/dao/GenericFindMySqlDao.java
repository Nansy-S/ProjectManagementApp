package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class GenericFindMySqlDao<T> extends BaseOperationMySqlDao<T> implements GenericFindDao<T> {

    public GenericFindMySqlDao() { }

    public abstract String getSqlSelectOne();

    @Override
    public T findOne(int id) {
        T object = null;

        String sql = getSqlSelectOne();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                object = getStatement(rs);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return object;
    }
}
