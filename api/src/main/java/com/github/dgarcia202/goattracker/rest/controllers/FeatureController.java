package com.github.dgarcia202.goattracker.rest.controllers;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import com.github.dgarcia202.goattracker.entities.builders.FeatureBuilder;
import com.github.dgarcia202.goattracker.exceptions.MissingFoundationDataException;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.FeatureStatusRepository;
import com.github.dgarcia202.goattracker.rest.rto.AddFeatureRto;
import com.github.dgarcia202.goattracker.services.AddFeatureService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FeatureController {

    private FeatureRepository featureRepository;

    private FeatureStatusRepository featureStatusRepository;

    private AddFeatureService addFeatureService;

    public FeatureController(
            FeatureRepository featureRepository,
            FeatureStatusRepository featureStatusRepository,
            AddFeatureService addFeatureService) {

        this.featureRepository = featureRepository;
        this.featureStatusRepository = featureStatusRepository;
        this.addFeatureService = addFeatureService;
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

    @PostMapping("/features")
    public Feature addFeature(@Valid @RequestBody AddFeatureRto req)
            throws MissingFoundationDataException {

        return addFeatureService.add(req);
    }
}
