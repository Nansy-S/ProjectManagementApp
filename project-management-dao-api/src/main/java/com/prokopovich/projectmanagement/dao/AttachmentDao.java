package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.exception.DaoException;
import com.prokopovich.projectmanagement.model.Attachment;

import java.util.Collection;

public interface AttachmentDao extends GenericFindDao<Attachment> {

    boolean update(Attachment attachment) throws DaoException;

    boolean delete(int attachmentId) throws DaoException;

    Collection<Attachment> findAllByTaskId(int taskId) throws DaoException;
}
