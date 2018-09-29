package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.FeatureStatusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {

    private FeatureRepository featureRepository;

    private FeatureStatusRepository featureStatusRepository;

    public FeatureController(FeatureRepository featureRepository, FeatureStatusRepository featureStatusRepository) {
        this.featureRepository = featureRepository;
        this.featureStatusRepository = featureStatusRepository;
    }

    @GetMapping("/features")
    public Iterable<Feature> getFeatures() {
        return featureRepository.findAll();
    }

    @GetMapping("/feature_statuses")
    public Iterable<FeatureStatus> getFeatureStatuses() {
        return featureStatusRepository.findAll();
    }
}
