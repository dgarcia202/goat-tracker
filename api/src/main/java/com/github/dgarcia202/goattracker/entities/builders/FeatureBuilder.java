package com.github.dgarcia202.goattracker.entities.builders;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import com.github.dgarcia202.goattracker.entities.Release;

import java.util.UUID;

public final class FeatureBuilder {
    private UUID id;
    private UUID projectId;
    private Release release;
    private String code;
    private String name;
    private String description;
    private FeatureStatus status;

    private FeatureBuilder() {
    }

    public static FeatureBuilder aFeature() {
        return new FeatureBuilder();
    }

    public FeatureBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public FeatureBuilder withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public FeatureBuilder withRelease(Release release) {
        this.release = release;
        return this;
    }

    public FeatureBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public FeatureBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FeatureBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FeatureBuilder withStatus(FeatureStatus status) {
        this.status = status;
        return this;
    }

    public Feature build() {
        Feature feature = new Feature();
        feature.setId(id);
        feature.setProjectId(projectId);
        feature.setRelease(release);
        feature.setCode(code);
        feature.setName(name);
        feature.setDescription(description);
        feature.setStatus(status);
        return feature;
    }
}
