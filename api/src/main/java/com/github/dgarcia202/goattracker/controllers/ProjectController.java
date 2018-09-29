package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.Project;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
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

    @GetMapping("/projects/{id}")
    public Project getProject(@PathVariable UUID id) {
        Optional<Project> option = projectRepository.findById(id);
        if (!option.isPresent()) {
            throw new NotFoundException();
        }

        return option.get();
    }

    @GetMapping("/projects/{id}/features")
    public Iterable<Feature> getProjectFeatures(@PathVariable UUID id) {
        Optional<Project> option = projectRepository.findById(id);
        if (!option.isPresent()) {
            throw new NotFoundException();
        }

        return featureRepository.findByProjectId(id);
    }

    @GetMapping("/projects/{projectId}/features/{featureId}")
    public Feature getProjectFeature(@PathVariable UUID projectId, @PathVariable UUID featureId) {
        Optional<Feature> option = featureRepository.findById(featureId);
        if (!option.isPresent()) {
            throw new NotFoundException();
        }

        Feature f = option.get();
        if (!f.getProjectId().equals(projectId)) {
            throw new NotFoundException();
        }

        return f;
    }
}
