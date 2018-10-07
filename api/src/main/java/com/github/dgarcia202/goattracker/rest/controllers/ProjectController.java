package com.github.dgarcia202.goattracker.rest.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.Project;
import com.github.dgarcia202.goattracker.entities.builders.ProjectBuilder;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.ProjectRepository;
import com.github.dgarcia202.goattracker.rest.rto.AddProjectRto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectRepository projectRepository;

    private FeatureRepository featureRepository;

    public ProjectController(ProjectRepository projectRepository, FeatureRepository featureRepository) {
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
    }

    @GetMapping
    public Iterable<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable UUID id) {
        Optional<Project> option = projectRepository.findById(id);
        if (!option.isPresent()) {
            throw new NotFoundException();
        }

        return option.get();
    }

    @GetMapping("/{id}/features")
    public Iterable<Feature> getProjectFeatures(@PathVariable UUID id) {
        Optional<Project> option = projectRepository.findById(id);
        if (!option.isPresent()) {
            throw new NotFoundException();
        }

        return featureRepository.findByProjectId(id);
    }

    @GetMapping("/{projectId}/features/{featureId}")
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

    @PostMapping
    public Project addProject(@Valid @RequestBody AddProjectRto req) {

        Project p = ProjectBuilder.aProject()
                .withId(UUID.randomUUID())
                .withName(req.getName())
                .withDescription(req.getDescription())
                .build();

        return projectRepository.save(p);
    }
}
