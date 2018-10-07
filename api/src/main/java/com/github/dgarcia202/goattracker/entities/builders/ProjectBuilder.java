package com.github.dgarcia202.goattracker.entities.builders;

import com.github.dgarcia202.goattracker.entities.Project;
import com.github.dgarcia202.goattracker.entities.Release;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProjectBuilder {
    private UUID id;
    private String name;
    private String description;
    private List<Release> releases = new ArrayList<>();

    private ProjectBuilder() {
    }

    public static ProjectBuilder aProject() {
        return new ProjectBuilder();
    }

    public ProjectBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder withReleases(List<Release> releases) {
        this.releases = releases;
        return this;
    }

    public Project build() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        project.setReleases(releases);
        return project;
    }
}
