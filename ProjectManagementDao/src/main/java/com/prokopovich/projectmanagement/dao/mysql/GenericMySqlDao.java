package com.prokopovich.projectmanagement.dao.mysql;

import com.prokopovich.projectmanagement.dao.GenericDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class GenericMySqlDao<T> implements GenericDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(GenericMySqlDao.class);

    T object;
    List<T> objectsList;

    GenericMySqlDao(T object, List<T> objectsList){
        this.object = object;
        this.objectsList = objectsList;
    }

    protected abstract T getStatement(ResultSet rs) throws SQLException;

    protected abstract void setStatement(T object, PreparedStatement statement) throws SQLException;

    public abstract String getSqlSelectAll();

    public abstract String getSqlSelectOne();

    public abstract String getSqlCreate();

    @Override
    public T create(T object) throws DaoException {
        LOGGER.trace("create object method is executed");
        String sql = getSqlCreate();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatement(object, statement);
            statement.executeUpdate();
            LOGGER.debug("New added object: " + object.toString());
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return object;
    }

    @Override
    public T findOne(int id) throws DaoException {
        LOGGER.trace("Find object method is executed");
        String sql = getSqlSelectOne();
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            object = getStatement(rs);
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return object;
    }

    @Override
    public Collection<T> findAll() throws DaoException {
        LOGGER.trace("Find all objects method is executed");
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
    public Collection<T> findByParameter(String sql, String parameter) throws DaoException {
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parameter);
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
}
