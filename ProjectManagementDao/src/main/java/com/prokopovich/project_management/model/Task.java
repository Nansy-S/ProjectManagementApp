package com.prokopovich.project_management.model;

import java.sql.Timestamp;

public class Task {
    int taskCode;
    String projectCode;
    String priority;
    String currentStatus;
    Timestamp dueDate;
    int estimationTime;
    int assignee;
    int reporter;
    String description;

}
