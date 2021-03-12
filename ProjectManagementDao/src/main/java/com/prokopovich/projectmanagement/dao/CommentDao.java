package com.prokopovich.projectmanagement.dao;

import com.prokopovich.projectmanagement.model.Comment;

import java.util.Collection;

public interface CommentDao {

    int createComment(Comment comment);

    boolean updateComment(int commentId);

    boolean deleteComment(int commentId);

    Collection<Comment> findAllByTaskId(int taskId);

    Collection<Comment> findAllByAuthor(int author);
}
