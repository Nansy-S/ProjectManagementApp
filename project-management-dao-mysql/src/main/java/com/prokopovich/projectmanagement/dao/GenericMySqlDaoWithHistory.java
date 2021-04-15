package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.factory.MySqlDaoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GenericMySqlDaoWithHistory<T> extends GenericFindMySqlDao<T> implements GenericDaoWithHistory<T> {

    private static final Logger LOGGER = LogManager.getLogger(GenericMySqlDaoWithHistory.class);

    public GenericMySqlDaoWithHistory() { }

    public abstract String getSqlSelectByReporterAndAction();

    @Override
    public Collection<T> findAllByReporterAndAction(int reporterId, String actionType) {
        T object;
        List<T> objectList = new ArrayList<>();

        String sql = getSqlSelectByReporterAndAction();
        LOGGER.trace("findAllByReporterAndAction method is executed - " +
                "reporterID = " + reporterId + ", actionType = " + actionType);
        try (Connection connection = MySqlDaoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reporterId);
            statement.setString(2, actionType);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                object = getStatement(rs);
                objectList.add(object);
            }
            LOGGER.debug("found objects by reporter: " + objectList.toString());
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return objectList;
    }
}
