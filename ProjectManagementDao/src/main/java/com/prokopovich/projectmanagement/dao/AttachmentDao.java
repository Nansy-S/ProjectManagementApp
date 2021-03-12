package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Attachment;

import java.util.Collection;

public interface AttachmentDao extends GenericDao<Attachment> {

    boolean updateAttachment(int attachmentId);

    boolean deleteAttachment(int attachmentId);

    Collection<Attachment> findAllByTaskId(int taskId);
}
