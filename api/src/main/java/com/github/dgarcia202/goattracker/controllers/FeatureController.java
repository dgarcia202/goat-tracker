package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.FeatureStatusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/features/{id}")
    public Feature getFeature(@PathVariable UUID id) {
        Optional<Feature> optional = featureRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException();
        }

        return optional.get();
    }

    @GetMapping("/feature-statuses")
    public Iterable<FeatureStatus> getFeatureStatuses() {
        return featureStatusRepository.findAllByOrderByOrderAsc();
    }
}
