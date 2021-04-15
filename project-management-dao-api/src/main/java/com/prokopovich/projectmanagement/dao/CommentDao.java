package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Comment;

import java.util.Collection;

public interface CommentDao extends BaseOperationDao<Comment> {

    boolean updateComment(Comment comment) throws DaoException;

    boolean deleteComment(int commentId) throws DaoException;

    Collection<Comment> findAllByTaskId(int taskId) throws DaoException;

    Collection<Comment> findAllByAuthor(int author) throws DaoException;
}
