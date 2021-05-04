package com.prokopovich.projectmanagement.webapp.rest;

import com.prokopovich.projectmanagement.enumeration.TaskPriority;
import com.prokopovich.projectmanagement.model.Task;
import com.prokopovich.projectmanagement.service.TaskService;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    private static final Logger LOGGER = LogManager.getLogger(TaskRestController.class);

    private final TaskService taskService;
    private final TokenManager tokenManager;

    @Autowired
    public TaskRestController(TaskService taskService, TokenManager tokenManager) {
        this.taskService = taskService;
        this.tokenManager = tokenManager;
    }

    @GetMapping(value = "/projects/{projectId}/tasks")
    @Secured({"ROLE_Project manager"})
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable int projectId) {
        LOGGER.trace("getTasksByProject method is executed");
        List<Task> taskList = taskService.getAllByProject(projectId);
        if(taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(value = "/reporter/{userId}/tasks")
    @Secured("ROLE_Project manager")
    public ResponseEntity<List<Task>> getAllByReporter(@PathVariable int userId) {
        LOGGER.trace("getAllByReporter method is executed");
        List<Task> taskList = taskService.getAllByReporter(userId);
        if(taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(value = "/assignee/{userId}/tasks")
    @Secured({"ROLE_Project manager", "ROLE_Developer", "ROLE_Tester"})
    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable int userId) {
        LOGGER.trace("getTasksByAssignee method is executed");
        List<Task> taskList = taskService.getAllByAssignee(userId);
        if(taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @GetMapping(value = "/tasks/{id}")
    @Secured({"ROLE_Project manager", "ROLE_Developer", "ROLE_Tester"})
    public ResponseEntity<Task> getTaskInfo(@PathVariable int id) {
        LOGGER.trace("getTaskInfo method is executed");
        Task task = taskService.getByTaskId(id);
        if(task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(value = "/tasks/add")
    @Secured("ROLE_Project manager")
    public ResponseEntity<Task> addTask(@RequestBody Task newTask) {
        LOGGER.trace("addTask method is executed");
        Task addedTask = taskService.addNewTask(newTask, tokenManager.getCurrentUser());
        if(addedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.trace("new task added");
        return new ResponseEntity<>(addedTask, HttpStatus.OK);
    }

    @PostMapping(value = "/tasks/{taskId}/change/status")
    @Secured({"ROLE_Project manager", "ROLE_Developer", "ROLE_Tester"})
    public ResponseEntity<Task> changeTaskStatus(@PathVariable int taskId, @RequestBody String newStatus) {
        LOGGER.trace("changeTaskStatus method is executed");
        Task updatedTask = taskService.changeStatus(taskId, newStatus, tokenManager.getCurrentUser());
        if(updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.trace("current status changed");
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PostMapping(value = "/tasks/{taskId}/change/assignee")
    @Secured({"ROLE_Project manager", "ROLE_Developer", "ROLE_Tester"})
    public ResponseEntity<Task> changeTaskAssignee(@PathVariable int taskId, @RequestBody int newAssigneeId) {
        LOGGER.trace("changeTaskAssignee method is executed");
        Task updatedTask = taskService.changeAssignee(taskId, newAssigneeId, tokenManager.getCurrentUser());
        if(updatedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LOGGER.trace("current status changed");
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @GetMapping(value = "/tasks/priorities")
    @Secured("ROLE_Project manager")
    public ResponseEntity<List<String>> getTaskPriorities() {
        LOGGER.trace("getTaskPriorities method is executed");
        List<String> priorityList = TaskPriority.getAllTitle();
        if(priorityList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(priorityList, HttpStatus.OK);
    }
}
