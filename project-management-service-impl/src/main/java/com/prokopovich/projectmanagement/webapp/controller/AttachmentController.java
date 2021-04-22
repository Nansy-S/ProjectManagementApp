package com.prokopovich.projectmanagement.webapp.controller;

import com.prokopovich.projectmanagement.model.Attachment;

import java.util.List;

public class AttachmentController {

    public void displayAttachmentsInfo(List<Attachment> attachmentList) {
        int numberAttachment = 0;

        System.out.println("\nAttachments: ");
        if (attachmentList.isEmpty() || attachmentList == null) {
            System.out.println("no attachments");
        } else {
            for (Attachment attachment : attachmentList) {
                numberAttachment++;
                System.out.println("\t" + numberAttachment +
                        ") " + attachment.getFile());
            }
        }
    }
}
