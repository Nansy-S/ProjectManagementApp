package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Attachment;

import java.util.Collection;

public interface AttachmentDAO {
    int createAttachment(Attachment attachment);
    boolean updateAttachment(int attachmentId);
    boolean deleteAttachment(int attachmentId);
    Collection<Attachment> findAllByTaskCodeProjectCode(int taskCode, String projectCode);
    Collection<Attachment> findAllByUserId(int userId);
}
