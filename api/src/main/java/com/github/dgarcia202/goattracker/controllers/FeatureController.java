package com.github.dgarcia202.goattracker.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {

    private FeatureRepository featureRepository;

    public FeatureController(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @GetMapping("/features")
    public Iterable<Feature> getFeatures() {
        return featureRepository.findAll();
    }

}
