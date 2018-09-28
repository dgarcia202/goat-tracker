package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Project;
import com.github.dgarcia202.goattracker.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public Iterable<Project> getProjects() {
        return projectRepository.findAll();
    }
}
