package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID> {
}
