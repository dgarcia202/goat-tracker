package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.Project;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProjectController {

    private ProjectRepository projectRepository;

    private FeatureRepository featureRepository;

    public ProjectController(ProjectRepository projectRepository, FeatureRepository featureRepository) {
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
    }

    @GetMapping("/projects")
    public Iterable<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{projectId}/features")
    public Iterable<Feature> getProjectFeatures(@PathVariable UUID projectId) {
        return featureRepository.findByProjectId(projectId);
    }
}
