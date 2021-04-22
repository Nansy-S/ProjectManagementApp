package com.prokopovich.projectmanagement.dao.hibernate;

import com.prokopovich.projectmanagement.dao.CommentDao;
import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Comment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

@Repository
public class CommentHibernateDao extends BaseOperationHibernateDao<Comment> implements CommentDao {

    private static final Logger LOGGER = LogManager.getLogger(CommentHibernateDao.class);

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CommentHibernateDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Comment.class);
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean updateComment(Comment comment) throws DaoException {
        LOGGER.trace("updateComment method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(comment);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean deleteComment(int commentId) throws DaoException {
        LOGGER.trace("deleteComment method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(
                    entityManager.find(Comment.class, commentId)
            );
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<Comment> findAllByTaskId(int taskId) throws DaoException {
        LOGGER.trace("findAllByTaskId method is executed - taskId = " + taskId);
        return findByParameter("taskId", taskId);
    }

    @Override
    public Collection<Comment> findAllByAuthor(int authorId) throws DaoException {
        LOGGER.trace("findAllByAuthor method is executed - authorId = " + authorId);
        return findByParameter("author", authorId);
    }
}
