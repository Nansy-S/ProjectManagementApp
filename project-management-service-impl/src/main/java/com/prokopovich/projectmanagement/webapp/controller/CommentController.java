package com.prokopovich.projectmanagement.webapp.controller;

import com.prokopovich.projectmanagement.model.Comment;

import java.util.List;

public class CommentController {

    public void displayCommentsInfo(List<Comment> commentList) {
        int numberComment = 0;

        System.out.println("\nComments: ");
        if (commentList.isEmpty() || commentList == null) {
            System.out.println("no comments");
        } else {
            for (Comment comment : commentList) {
                numberComment++;
                System.out.println("\t" + numberComment +
                        ") " + comment.getTitle() +
                        " - " + comment.getText() +
                        " - " + comment.getText());
            }
        }
    }
}
