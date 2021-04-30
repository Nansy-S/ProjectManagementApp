package com.prokopovich.projectmanagement.webapp.rest;


import com.prokopovich.App;
import com.prokopovich.projectmanagement.enumeration.AccountActionType;
import com.prokopovich.projectmanagement.enumeration.ProjectActionType;
import com.prokopovich.projectmanagement.model.Account;
import com.prokopovich.projectmanagement.model.Project;
import com.prokopovich.projectmanagement.model.User;
import com.prokopovich.projectmanagement.service.AccountService;
import com.prokopovich.projectmanagement.service.ProjectService;
import com.prokopovich.projectmanagement.service.UserService;
import com.prokopovich.projectmanagement.util.ObjectFormat;
import com.prokopovich.projectmanagement.webapp.util.jwt.TokenManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectRestController {

    private static final Logger LOGGER = LogManager.getLogger(ProjectRestController.class);

    private final ProjectService projectService;
    private final TokenManager tokenManager;

    @Autowired
    public ProjectRestController(ProjectService projectService, TokenManager tokenManager) {
        this.projectService = projectService;
        this.tokenManager = tokenManager;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Project>> getProjectsByReporter() {
        LOGGER.trace("getProjectsByReporter method is executed");
        List<Project> projects = projectService.getAllByReporterAndAction(
                tokenManager.getCurrentUser().getAccountId(),
                ProjectActionType.CREATE.getTitle());
        if(projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    //@GetMapping(value = "/{id}")
    //public ResponseEntity<User> getUserInfo(@PathVariable int id) {
    //    LOGGER.trace("getUserInfo method is executed");
    //    User user = userService.getByUserId(id);
    //    if(user == null) {
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }
    //    return new ResponseEntity<>(user, HttpStatus.OK);
    //}

    @PostMapping(value = "/add")
    public ResponseEntity<Project> addProject(@RequestBody Project newProject) {
        LOGGER.trace("addProject method is executed");
        Project addedProject = projectService.addNewProject(newProject, tokenManager.getCurrentUser());
        LOGGER.trace("new project added");
        return new ResponseEntity<>(addedProject, HttpStatus.OK);
    }
}
