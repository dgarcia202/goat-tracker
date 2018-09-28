package com.github.dgarcia202.goattracker.repositories;

import com.github.dgarcia202.goattracker.entities.Team;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TeamRepository extends PagingAndSortingRepository<Team, UUID> {
}
