package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.TaskActionDao;
import com.prokopovich.projectmanagement.dao.TaskDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class TaskHibernateDao extends GenericHibernateDaoWithHistory<Task> implements TaskDao {

    private static final Logger LOGGER = LogManager.getLogger(TaskHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;
    private final TaskActionDao taskActionDao;

    @Autowired
    public TaskHibernateDao(EntityManagerFactory entityManagerFactory, TaskActionDao taskActionDao) {
        super(entityManagerFactory, Task.class);
        this.entityManagerFactory = entityManagerFactory;
        this.taskActionDao = taskActionDao;
    }

    public String getSqlSelectByReporterAndAction() {
        return "SQL_SELECT_BY_REPORTER_AND_ACTION";
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
        List<Task> taskList = (List<Task>) findByParameter("projectId", projectId);
        //taskList = setListActions(taskList);
        return taskList;
    }

    @Override
    public Collection<Task> findAllByAssignee(int assignee) throws DaoException {
        LOGGER.trace("findAllByAssignee method is executed - assigneeId = " + assignee);
        List<Task> taskList = (List<Task>) findByParameter("assignee", assignee);
        taskList = setListActions(taskList);
        return taskList;
    }

    @Override
    public Collection<Task> findAllByReporter(int reporter) throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporter = " + reporter);
        List<Task> taskList = (List<Task>) findByParameter("reporter", reporter);
        taskList = setListActions(taskList);
        return taskList;
    }

    @Override
    public Collection<Task> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        List<Task> taskList = (List<Task>) findByParameter("currentStatus", currentStatus);
        taskList = setListActions(taskList);
        return taskList;
    }

    private List<Task> setListActions(List<Task> taskList) {
        for(Task task : taskList) {
            task.setTaskActions(
                    (List<TaskAction>) taskActionDao.findAllByTaskId(task.getTaskId()));
        }
        return taskList;
    }
}
