package com.prokopovich.projectmanagement.controller;

import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.ProjectActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectStatus;
import com.prokopovich.projectmanagement.factory.ServiceFactory;
import com.prokopovich.projectmanagement.factory.ServiceFactoryImpl;
import com.prokopovich.projectmanagement.model.*;
import com.prokopovich.projectmanagement.service.ProjectService;
import com.prokopovich.projectmanagement.util.ObjectFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class ProjectController {

    private static final Logger LOGGER = LogManager.getLogger(ProjectController.class);
    private static final Scanner INPUT = new Scanner(System.in);

    private final ServiceFactory service = new ServiceFactoryImpl();
    private final ProjectService projectService = service.getProjectService();

    public void displayProjects(List<Project> projectList) {
        int number = 0;

        System.out.println(" #) Project code - Status - Due Date - Summary");
        for(Project project: projectList) {
            number++;
            System.out.println(number +
                    ") " + project.getProjectCode() +
                    " - " + project.getCurrentStatus() +
                    " - " + ObjectFormat.formattingDateTime(project.getDueDate()) +
                    " - " + project.getSummary());
        }
    }

    public List<Project> displayProjectsByReporter() {
        LOGGER.trace("displayProjectsByReporter method is executed");
        List<Project> projectList = projectService.getAllByReporterAndAction(App.getCurrentUser(),
                ProjectActionType.CREATE.getTitle());
        displayProjects(projectList);

        return projectList;
    }

    public List<Project> displayActiveProjects() {
        List<Project> projectList = projectService.getAllByReporterAndStatus(App.getCurrentUser(),
                ProjectStatus.OPEN.getTitle(), ProjectStatus.IN_PROGRESS.getTitle());
        displayProjects(projectList);
        return projectList;
    }

    public Project chooseProject(List<Project> projectList) {
        Project project;

        System.out.print("Enter record number to view (edit) detailed information or 0 to exit: ");
        int chosenProjectNumber = INPUT.nextInt();
        if (chosenProjectNumber != 0) {
            chosenProjectNumber--;
            project = projectList.get(chosenProjectNumber);
            return project;
        }
        else {
            return null;
        }
    }

    public void displayProjectInfo(Project project) {
        int numberAction = 0;

        LOGGER.trace("displayProjectInfo method is executed");
        if (project != null) {
            System.out.println("Project info:" +
                    "\n\tproject code: " + project.getProjectCode() +
                    "\n\tstatus: " + project.getCurrentStatus() +
                    "\n\tdue date: " + ObjectFormat.formattingDateTime(project.getDueDate()) +
                    "\n\tsummary: " + project.getSummary());
            System.out.println("Actions: ");
            for (ProjectAction action : project.getProjectActions()) {
                numberAction++;
                System.out.println("\t" + numberAction +
                        ") " + action.getAction().getType() +
                        " - " + ObjectFormat.formattingDateTime(action.getAction().getDatetime()) +
                        " - " + action.getAction().getReporterInfo().getName() +
                        " " + action.getAction().getReporterInfo().getPatronymic() +
                        " " + action.getAction().getReporterInfo().getSurname()
                );
            }
        }
    }

    public void addProject() {
        Project newProject = new Project();

        LOGGER.trace("addProject method is executed");
        System.out.println("Enter new project: ");
        System.out.print("\tproject code: ");
        newProject.setProjectCode(INPUT.nextLine());
        System.out.print("\tdue date (dd.MM.yyyy hh:mm:ss): ");

        String dateTime = INPUT.nextLine();
        newProject.setDueDate(ObjectFormat.formattingInputDateTime(dateTime));

        System.out.print("\tsummary: ");
        newProject.setSummary(INPUT.nextLine());
        projectService.addNewProject(newProject, App.getCurrentUser());
        LOGGER.trace("new project added");
    }

    public void editProject() {
        LOGGER.trace("editProject method is executed");
        List<Project> projectList = displayProjectsByReporter();
        Project project = chooseProject(projectList);
        if (project != null) {
            displayProjectInfo(project);
            System.out.println("Do you want to edit information about this project? (1 - Yes, 2 - No)");
            int choice = INPUT.nextInt();
            if (choice == 1) {
                LOGGER.trace("old project information - " + project.toString());
                System.out.println("Select a field to edit:");
                System.out.println("1) due date \n2) summary \nYour choice: ");
                int chosenField = INPUT.nextInt();
                System.out.print("Enter a new value: ");
                INPUT.nextLine();
                switch (chosenField) {
                    case 1:
                        String dateTime = INPUT.nextLine();
                        project.setDueDate(ObjectFormat.formattingInputDateTime(dateTime));
                        break;
                    case 2:
                        project.setSummary(INPUT.nextLine());
                        break;
                    default:
                        System.out.println("Invalid character! Try again.");
                        break;
                }
                if (projectService.editProject(project, App.getCurrentUser())) {
                    LOGGER.trace("new project information - " + project.toString());
                    LOGGER.trace("project information successfully edited");
                }
            } else {
                LOGGER.trace("cancel editing");
            }
        }
    }

    public void closeProject() {
        LOGGER.trace("closeProject method is executed");
        List<Project> projectList = projectService.getAllByReporterAndStatus(App.getCurrentUser(),
                ProjectStatus.IN_PROGRESS.getTitle());
        changeStatusProject(projectList, ProjectStatus.CLOSED.getTitle(),
                ProjectActionType.CLOSED.getTitle());
    }

    public void deleteProject() {
        LOGGER.trace("closeProject method is executed");
        List<Project> projectList = projectService.getAllByReporterAndStatus(App.getCurrentUser(),
                ProjectStatus.OPEN.getTitle(), ProjectStatus.IN_PROGRESS.getTitle());
        changeStatusProject(projectList, ProjectStatus.DELETED.getTitle(),
                ProjectActionType.DELETE.getTitle());
    }

    public void changeStatusProject(List<Project> projectList, String newProjectStatus,
                                    String actionType) {
        displayProjects(projectList);
        System.out.print("Enter record number to change status or 0 to exit: ");
        int chosenProjectNumber = INPUT.nextInt();
        if (chosenProjectNumber != 0) {
            chosenProjectNumber--;
            Project project = projectList.get(chosenProjectNumber);
            System.out.print("Current status: " + project.getCurrentStatus() +
                    "\nChange project status? (1 - Yes, 2 - No): ");
            int choice = INPUT.nextInt();
            if (choice == 1) {
                project.setCurrentStatus(newProjectStatus);
                if (projectService.changeStatus(project, App.getCurrentUser(), actionType)) {
                    LOGGER.trace("project status successfully edited - Current status: " +
                            project.getCurrentStatus());
                }
            } else {
                LOGGER.trace("cancel editing");
            }
        } else {
            LOGGER.trace("cancel editing");
        }
    }

    public void setProjectStatusInProgress(int projectId) {
        projectService.setProjectStatusInProgress(projectId);
    }
}
