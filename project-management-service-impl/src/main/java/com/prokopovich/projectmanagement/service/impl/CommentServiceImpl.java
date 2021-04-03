package com.prokopovich.projectmanagement.service.impl;

import com.prokopovich.projectmanagement.dao.CommentDao;
import com.prokopovich.projectmanagement.service.CommentService;

public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
