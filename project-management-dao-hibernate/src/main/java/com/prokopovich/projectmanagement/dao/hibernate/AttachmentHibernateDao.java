package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.AttachmentDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

@Repository
public class AttachmentHibernateDao extends GenericHibernateDao<Attachment> implements AttachmentDao {

    private static final Logger LOGGER = LogManager.getLogger(AttachmentHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AttachmentHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Attachment.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean update(Attachment attachment) throws DaoException {
        LOGGER.trace("updateAttachment method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(attachment);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean delete(int attachmentId) throws DaoException {
        LOGGER.trace("deleteAttachment method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(findOne(attachmentId));
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Attachment> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter("taskId", taskId);
    }
}
