package com.prokopovich.project_management.dao;

import com.prokopovich.project_management.model.Comment;

import java.util.Collection;

public interface CommentDAO {
    int createComment(Comment comment);
    boolean updateComment(int commentId);
    boolean deleteComment(int commentId);
    Collection<Comment> findAllByTaskCodeProjectCode(int taskCode, String projectCode);
    Collection<Comment> findAllByAuthor(int author);
}
