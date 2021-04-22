package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.GenericDaoWithHistory;
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
import java.util.Collection;
import java.util.List;

@Repository
public abstract class GenericHibernateDaoWithHistory<T> extends GenericFindHibernateDao<T> implements GenericDaoWithHistory<T> {

    private static final Logger LOGGER = LogManager.getLogger(GenericHibernateDaoWithHistory.class);

    private final EntityManagerFactory entityManagerFactory;

    private Class<T> className;

    @Autowired
    public GenericHibernateDaoWithHistory(EntityManagerFactory entityManagerFactory, Class<T> className) {
        super(entityManagerFactory, className);
        this.entityManagerFactory = entityManagerFactory;
        this.className = className;
    }

    public abstract String getSqlSelectByReporterAndAction();

    @Override
    public Collection<T> findAllByReporterAndAction(int reporterId, String actionType) {
        LOGGER.trace("findAllByReporterAndAction method is executed - reporterId = " + reporterId
                + ", actionType = " + actionType);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = getSqlSelectByReporterAndAction() +
                    "WHERE a.reporter =: reporterId AND a.type =: actionType)";

            Query query = entityManager.createQuery(sql);
            query.setParameter("reporterId", reporterId);
            query.setParameter("actionType", actionType);

            return query.getResultList();

            //CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            //CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(className);
            //Root<T> tRoot = criteriaQuery.from(className);
            //Predicate predicateReporter = criteriaBuilder.equal(tRoot.get("reporter"), reporterId);
            //Predicate predicateActionType = criteriaBuilder.equal(tRoot.get("type"), actionType);
            //criteriaQuery.where(predicateReporter, predicateActionType);
            //return entityManager.createQuery(criteriaQuery).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
