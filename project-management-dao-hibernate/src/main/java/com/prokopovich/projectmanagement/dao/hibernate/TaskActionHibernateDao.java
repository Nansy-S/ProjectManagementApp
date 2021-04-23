package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.TaskActionDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.TaskAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class TaskActionHibernateDao extends GenericHibernateDao<TaskAction> implements TaskActionDao {

    private static final Logger LOGGER = LogManager.getLogger(TaskActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public TaskActionHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, TaskAction.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Collection<TaskAction> findAllByTaskId(int taskId) throws SQLException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter("taskId", taskId);
    }

    @Override
    public Collection<TaskAction> findAllByReporter(int reporterId) throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporterID = " + reporterId);
        return findByParameter("reporter", reporterId);
    }
}
