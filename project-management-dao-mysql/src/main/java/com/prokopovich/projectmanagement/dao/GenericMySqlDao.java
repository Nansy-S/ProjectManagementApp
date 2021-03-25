package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public abstract class GenericMySqlDao<T> implements GenericDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(GenericMySqlDao.class);

    T object;
    List<T> objectsList;

    GenericMySqlDao(T object, List<T> objectsList) {
        this.object = object;
        this.objectsList = objectsList;
    }

    protected abstract T getStatement(ResultSet rs) throws SQLException;

    protected abstract void setStatement(T object, PreparedStatement statement) throws SQLException;

    public abstract String getSqlSelectAll();

    public abstract String getSqlSelectOne();

    public abstract String getSqlCreate();

    public abstract String getSqlLastInsert();

    @Override
    public T create(T object, int id) {
        String sql = getSqlCreate();

        LOGGER.trace("create object method is executed");
        try (Connection connection = MySqlDaoFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setStatement(object, statement);
            statement.executeUpdate();
            if (id == 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        T ob = findOne(id);
        LOGGER.debug("new added object - " + ob.toString());
        return ob;
    }

    @Override
    public T findOne(int id) {
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

    @Override
    public Collection<T> findAll() {
        String sql = getSqlSelectAll();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                object = getStatement(rs);
                LOGGER.debug(object.toString());
            }
            objectsList.add(object);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return objectsList;
    }

    @Override
    public Collection<T> findByParameter(String sql, String parameter) {
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parameter);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                object = getStatement(rs);
                LOGGER.debug("found objects - " + object.toString());
            }
            objectsList.add(object);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return objectsList;
    }

    @Override
    public Collection<T> findByParameter(String sql, int parameter) {
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, parameter);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                object = getStatement(rs);
                LOGGER.debug("found objects - " + object.toString());
            }
            objectsList.add(object);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return objectsList;
    }
}
