package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.AttachmentDao;
import com.prokopovich.projectmanagement.service.AttachmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentDao attachmentDao;

    public AttachmentServiceImpl(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }
}
