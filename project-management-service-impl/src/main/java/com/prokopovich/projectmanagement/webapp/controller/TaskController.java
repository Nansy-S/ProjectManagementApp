package com.prokopovich.projectmanagement.webapp.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.TaskPriority;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.TaskService;
import com.prokopovich.projectmanagement.util.ObjectFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class TaskController {

    private static final Logger LOGGER = LogManager.getLogger(TaskController.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private final ServiceFactory service = new ServiceFactoryImpl();
    private final TaskService taskService = service.getTaskService();
    private final AccountService accountService = service.getAccountService();
    private final ProjectController projectController = new ProjectController();
    private final CommentController commentController = new CommentController();
    private final AttachmentController attachmentController = new AttachmentController();

    public void displayTasks(List<Task> taskList) {
        int number = 0;

        System.out.println(" #) Task code - Priority - Status - Due Date - Estimation time - Assignee - Description");
        for(Task task: taskList) {
            number++;
            System.out.println(number +
                    ") " + task.getTaskCode() +
                    " - " + task.getPriority() +
                    " - " + task.getCurrentStatus() +
                    " - " + ObjectFormat.formattingDateTime(task.getDueDate()) +
                    " - " + task.getEstimationTime() +
                    " - " + task.getAssignee() +
                    " - " + task.getDescription());
        }
    }

    public List<Task> displayTasksByProject() {
        List<Task> taskList;
        List<Project> projectList;
        Project project;

        LOGGER.trace("displayTasksByProject method is executed");
        projectList = projectController.displayProjectsByReporter();
        project = projectController.chooseProject(projectList);
        taskList = taskService.getAllByProject(project.getProjectId());
        displayTasks(taskList);
        return taskList;
    }

    public void displayTaskInfo(Task task) {
        int numberAction = 0;

        LOGGER.trace("displayTaskInfo method is executed");
        if (task != null) {
            System.out.println("Task info:" +
                    "\n\ttask code: " + task.getTaskCode() +
                    "\n\tpriority: " + task.getPriority() +
                    "\n\tstatus: " + task.getCurrentStatus() +
                    "\n\tdue date: " + ObjectFormat.formattingDateTime(task.getDueDate()) +
                    "\n\testimation time: " + task.getEstimationTime() +
                    "\n\tdescription: " + task.getDescription());
            Account assigneeInfo = accountService.findByAccountId(task.getAssignee());
            System.out.println("Assignee:" +
                    "\n\temail: " + assigneeInfo.getEmail() +
                    "\n\trole: " + assigneeInfo.getRole() +
                    "\n\tfull name: " + assigneeInfo.getName() +
                    " " + assigneeInfo.getPatronymic() +
                    " " + assigneeInfo.getSurname());
            System.out.println("\nActions: ");
            for (TaskAction action : task.getTaskActions()) {
                numberAction++;
                System.out.println("\t" + numberAction +
                        ") " + action.getAction().getType() +
                        " - " + ObjectFormat.formattingDateTime(action.getAction().getDatetime()) +
                        " - " + action.getAction().getReporterInfo().getName() +
                        " " + action.getAction().getReporterInfo().getPatronymic() +
                        " " + action.getAction().getReporterInfo().getSurname()
                );
            }
            //attachmentController.displayAttachmentsInfo(task.getAttachmentList());
            //commentController.displayCommentsInfo(task.getCommentList());
        }
    }

    public Task chooseTask(List<Task> taskList) {
        Task task;

        System.out.print("Enter record number to view (edit) detailed information or 0 to exit: ");
        int chosenProjectNumber = INPUT.nextInt();
        if (chosenProjectNumber != 0) {
            chosenProjectNumber--;
            task = taskList.get(chosenProjectNumber);
            return task;
        }
        else {
            return null;
        }
    }

    public void addTask() {
        Task newTask = new Task();
        Project project;
        List<Project> projectList;

        LOGGER.trace("addTask method is executed");
        projectList = projectController.displayActiveProjects();
        System.out.print("Enter project number to add task or 0 to exit: ");
        int chosenProjectNumber = INPUT.nextInt();
        if (chosenProjectNumber != 0) {
            chosenProjectNumber--;
            project = projectList.get(chosenProjectNumber);
            newTask.setProjectId(project.getProjectId());
            System.out.println("Enter new task: ");
            System.out.println("\tchoose priority task: ");
            newTask.setPriority(enterTaskPriority());
            INPUT.nextLine();
            System.out.print("\tdue date (dd.MM.yyyy hh:mm:ss): ");
            String dateTime = INPUT.nextLine();
            newTask.setDueDate(ObjectFormat.formattingInputDateTime(dateTime));
            System.out.print("\testimation time: ");
            newTask.setEstimationTime(INPUT.nextInt());
            System.out.print("\tdescription: ");
            newTask.setDescription(INPUT.nextLine()); //
            newTask.setDescription(INPUT.nextLine());
            taskService.addNewTask(newTask, App.getCurrentUser());
            LOGGER.trace("new project added");
        } else {
            LOGGER.trace("cancel adding task");
        }
    }

    public String enterTaskPriority() {
        String priority = "priority";

        System.out.print("\t\t1) " + TaskPriority.BLOCKER.getTitle() +
                "\n\t\t2) " + TaskPriority.CRITICAL.getTitle() +
                "\n\t\t3) " + TaskPriority.MAJOR.getTitle() +
                "\n\t\t4) " + TaskPriority.NORMAL.getTitle() +
                "\n\t\t5) " + TaskPriority.MINOR.getTitle() +
                "\n\tYour choice: ");
        int chosenPriority = INPUT.nextInt();
        switch (chosenPriority) {
            case 1:
                priority = TaskPriority.BLOCKER.getTitle();
                break;
            case 2:
                priority = TaskPriority.CRITICAL.getTitle();
                break;
            case 3:
                priority = TaskPriority.MAJOR.getTitle();
                break;
            case 4:
                priority = TaskPriority.NORMAL.getTitle();
                break;
            case 5:
                priority = TaskPriority.MINOR.getTitle();
                break;
            default:
                System.out.println("Invalid character! Try again.");
                break;
        }
        return priority;
    }

    public void editTask() {
        LOGGER.trace("editTask method is executed");
        List<Task> taskList = displayTasksByProject();
        Task task = chooseTask(taskList);
        if (task != null) {
            displayTaskInfo(task);
            System.out.println("Do you want to edit information about this task? (1 - Yes, 2 - No)");
            int choice = INPUT.nextInt();
            if (choice == 1) {
                LOGGER.trace("old task information - " + task.toString());
                System.out.println("Select a field to edit:");
                System.out.println("1) priority \n2) due date \n3) estimation time \n4) description \nYour choice: ");
                int chosenField = INPUT.nextInt();
                System.out.print("Enter a new value: ");
                INPUT.nextLine();
                switch (chosenField) {
                    case 1:
                        task.setPriority(enterTaskPriority());
                        break;
                    case 2:
                        String dateTime = INPUT.nextLine();
                        task.setDueDate(ObjectFormat.formattingInputDateTime(dateTime));
                        break;
                    case 3:
                        task.setEstimationTime(INPUT.nextInt());
                        break;
                    case 4:
                        task.setDescription(INPUT.nextLine());
                        break;
                    default:
                        System.out.println("Invalid character! Try again.");
                        break;
                }
                if (taskService.editTask(task, App.getCurrentUser())) {
                    LOGGER.trace("new task information - " + task.toString());
                    LOGGER.trace("task information successfully edited");
                }
            } else {
                LOGGER.trace("cancel editing");
            }
        }
    }
}
