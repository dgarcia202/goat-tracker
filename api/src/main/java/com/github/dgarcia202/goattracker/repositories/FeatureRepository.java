package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Feature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, UUID> {

    @Query("SELECT f FROM Feature f WHERE projectId = :projectId")
    List<Feature> findByProjectId(@Param("projectId") UUID projectId);
}
