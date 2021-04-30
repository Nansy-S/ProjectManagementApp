package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.ProjectActionDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.ProjectAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class ProjectActionHibernateDao extends GenericHibernateDao<ProjectAction> implements ProjectActionDao {

    private static final Logger LOGGER = LogManager.getLogger(ProjectActionHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProjectActionHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, ProjectAction.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Collection<ProjectAction> findAllByProjectId(int projectId) throws DaoException {
        LOGGER.trace("findAllByProjectId method is executed - projectId = " + projectId);
        return findByParameter("projectId", projectId);
    }

    @Override
    public Collection<ProjectAction> findAllByReporter(int reporterId) throws DaoException {
        LOGGER.trace("findAllByReporter method is executed - reporterID = " + reporterId);
        return findByParameter("reporter", reporterId);
    }
}
