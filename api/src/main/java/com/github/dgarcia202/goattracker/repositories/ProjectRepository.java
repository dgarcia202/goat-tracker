package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends CrudRepository<Project, UUID> {
}
