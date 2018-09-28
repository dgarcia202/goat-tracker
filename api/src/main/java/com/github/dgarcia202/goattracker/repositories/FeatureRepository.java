package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Feature;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FeatureRepository extends CrudRepository<Feature, UUID> {
}
