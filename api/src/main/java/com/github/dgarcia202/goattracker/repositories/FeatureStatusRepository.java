package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.FeatureStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeatureStatusRepository extends CrudRepository<FeatureStatus, UUID> {

    Iterable<FeatureStatus> findAllByOrderByOrderAsc();

    Optional<FeatureStatus> findOneByDescription(String description);
}
