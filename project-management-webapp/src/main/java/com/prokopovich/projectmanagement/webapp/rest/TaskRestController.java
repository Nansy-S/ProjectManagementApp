package com.prokopovich.projectmanagement.webapp.rest;

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
@Transactional
@RequestMapping("/api")
@Secured("Project manager")
@CrossOrigin(origins = "http://localhost:4200")
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
    @Secured("Project manager")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable int projectId) {
        LOGGER.trace("getTasksByProject method is executed");
        List<Task> taskList = taskService.getAllByProject(projectId);
        if(taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping(value = "/tasks/add")
    @Secured("Project manager")
    public ResponseEntity<Task> addTask(@RequestBody Task newTask) {
        LOGGER.trace("addTask method is executed");
        Task addedTask = taskService.addNewTask(newTask, tokenManager.getCurrentUser());
        LOGGER.trace("new task added");
        return new ResponseEntity<>(addedTask, HttpStatus.OK);
    }
}
