package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.TaskDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Task;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

public class TaskHibernateDao extends GenericHibernateDaoWithHistory<Task> implements TaskDao {

    private static final Logger LOGGER = LogManager.getLogger(TaskHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    public TaskHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Task.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean updateTask(Task task) throws DaoException {
        LOGGER.trace("updateTask method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(task);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Task> findAllByProjectId(int projectId) throws DaoException {
        LOGGER.trace("findAllByProjectId method is executed - projectId = " + projectId);
        return findByParameter("projectId", projectId);
    }

    @Override
    public Collection<Task> findAllByAssignee(int assignee) throws DaoException {
        LOGGER.trace("findAllByAssignee method is executed - assigneeId = " + assignee);
        return findByParameter("assignee", assignee);
    }

    @Override
    public Collection<Task> findAllByReporter(int reporter) throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporter = " + reporter);
        return findByParameter("reporter", reporter);
    }

    @Override
    public Collection<Task> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        return findByParameter("currentStatus", currentStatus);
    }
}
