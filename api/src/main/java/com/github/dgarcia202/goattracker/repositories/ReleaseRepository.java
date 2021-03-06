package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Release;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReleaseRepository extends CrudRepository<Release, UUID> {
}
