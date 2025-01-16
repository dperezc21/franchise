package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchDBRepository extends CrudRepository<Branch, Long> {
}
