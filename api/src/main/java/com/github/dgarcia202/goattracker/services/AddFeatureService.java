package com.github.dgarcia202.goattracker.services;

import com.github.dgarcia202.goattracker.entities.Feature;
import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import com.github.dgarcia202.goattracker.entities.Release;
import com.github.dgarcia202.goattracker.entities.builders.FeatureBuilder;
import com.github.dgarcia202.goattracker.exceptions.MissingFoundationDataException;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.repositories.FeatureRepository;
import com.github.dgarcia202.goattracker.repositories.FeatureStatusRepository;
import com.github.dgarcia202.goattracker.repositories.ReleaseRepository;
import com.github.dgarcia202.goattracker.rest.rto.AddFeatureRto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddFeatureService {

    private FeatureRepository featureRepository;

    private ReleaseRepository releaseRepository;

    private FeatureStatusRepository featureStatusRepository;

    public AddFeatureService(
            FeatureRepository featureRepository,
            ReleaseRepository releaseRepository,
            FeatureStatusRepository featureStatusRepository) {

        this.featureRepository = featureRepository;
        this.releaseRepository = releaseRepository;
        this.featureStatusRepository = featureStatusRepository;
    }

    public Feature add(AddFeatureRto rto) throws MissingFoundationDataException {

        Optional<FeatureStatus> fs = featureStatusRepository.findOneByDescription("Defined");
        if (!fs.isPresent()) {
            throw new MissingFoundationDataException("Feature Status 'Defined' is missing.'");
        }

        Optional<Release> r = releaseRepository.findById(rto.getReleaseId());
        if (!r.isPresent()) {
            throw new NotFoundException();
        }

        Feature f = FeatureBuilder.aFeature()
                .withId(UUID.randomUUID())
                .withCode("12345678")       // TODO: please fix this.
                .withDescription(rto.getDescription())
                .withName(rto.getName())
                .withProjectId(rto.getProjectId())
                .withRelease(r.get())
                .withStatus(fs.get())
                .build();

        return featureRepository.save(f);
    }
}
