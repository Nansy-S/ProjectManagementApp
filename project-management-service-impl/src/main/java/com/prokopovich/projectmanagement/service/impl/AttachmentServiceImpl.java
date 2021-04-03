package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AttachmentDao;
import com.prokopovich.projectmanagement.service.AttachmentService;

public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentDao attachmentDao;

    public AttachmentServiceImpl(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }
}
