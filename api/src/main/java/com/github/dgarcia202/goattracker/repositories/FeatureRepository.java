package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, UUID> {
}
