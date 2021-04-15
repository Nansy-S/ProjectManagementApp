package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.ProjectDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Project;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class ProjectHibernateDao extends GenericHibernateDaoWithHistory<Project> implements ProjectDao {

    private static final Logger LOGGER = LogManager.getLogger(ProjectHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    public ProjectHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Project.class);
        this.entityManagerFactory = entityManagerFactory;
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
        return findByParameter("currentStatus", currentStatus);
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
            return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
