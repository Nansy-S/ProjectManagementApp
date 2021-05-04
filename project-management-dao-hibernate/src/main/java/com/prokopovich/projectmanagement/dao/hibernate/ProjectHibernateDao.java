package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.ProjectActionDao;
import com.prokopovich.projectmanagement.dao.ProjectDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Project;
import com.prokopovich.projectmanagement.model.ProjectAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public class ProjectHibernateDao extends GenericHibernateDaoWithHistory<Project> implements ProjectDao {

    private static final Logger LOGGER = LogManager.getLogger(ProjectHibernateDao.class);
    private static final String SQL_SELECT_BY_REPORTER_AND_ACTION = "SELECT p FROM Project p WHERE p.projectId IN " +
            "(SELECT pa.projectId FROM ProjectAction pa INNER JOIN Action a ON a.actionId = pa.actionId ";

    private final EntityManagerFactory entityManagerFactory;
    private final ProjectActionDao projectActionDao;

    @Autowired
    public ProjectHibernateDao(EntityManagerFactory entityManagerFactory, ProjectActionDao projectActionDao) {
        super(entityManagerFactory, Project.class);
        this.entityManagerFactory = entityManagerFactory;
        this.projectActionDao = projectActionDao;
    }

    public String getSqlSelectByReporterAndAction() {
        return SQL_SELECT_BY_REPORTER_AND_ACTION;
    }

    @Override
    public Collection<Project> findAllByReporterAndAction(int reporterId, String actionType) {
        List<Project> projectList = (List<Project>) super.findAllByReporterAndAction(reporterId, actionType);
        projectList = setListActions(projectList);
        return projectList;
    }

    @Override
    public boolean update(Project project) throws DaoException {
        LOGGER.trace("updateProject method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Project> findAllByCurrentStatus(String currentStatus) throws DaoException {
        LOGGER.trace("findAllByCurrentStatus method is executed - currentStatus = " + currentStatus);
        List<Project> projectList = (List<Project>) findByParameter("currentStatus", currentStatus);
        projectList = setListActions(projectList);
        return projectList;
    }

    @Override
    public Collection<Project> findAllByReporterAndStatus(int reporterId, String... statuses) throws DaoException {
        LOGGER.trace("findAllByReporterAndStatus method is executed - reporterId = " + reporterId +
                ", statuses: " + statuses.toString());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
            Root<Project> tRoot = criteriaQuery.from(Project.class);
            Predicate predicateId = criteriaBuilder.equal(tRoot.get("reporter"), reporterId);
            Predicate[] predicates = new Predicate[3];
            for(int i = 0; i < 3; i++) {
                predicates[i] = criteriaBuilder.equal(tRoot.get("currentStatus"), "status");
            }
            for(int i = 0; i < statuses.length; i++) {
                predicates[i] = criteriaBuilder.equal(tRoot.get("currentStatus"), statuses[i]);
            }
            criteriaQuery.select(tRoot).where(
                    criteriaBuilder.and(predicateId,
                            criteriaBuilder.or(predicates[0], predicates[1], predicates[2]))
            );
            List<Project> projectList = entityManager.createQuery(criteriaQuery).getResultList();
            projectList = setListActions(projectList);
            return projectList;
        } finally {
            entityManager.close();
        }
    }

    private List<Project> setListActions(List<Project> projectList) {
        for(Project project : projectList) {
            project.setProjectActions(
                    (List<ProjectAction>) projectActionDao.findAllByProjectId(project.getProjectId()));
        }
        return projectList;
    }
}
