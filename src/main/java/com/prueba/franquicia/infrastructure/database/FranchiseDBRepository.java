package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Franchise;
import org.springframework.data.repository.CrudRepository;

public interface FranchiseDBRepository extends CrudRepository<Franchise, Long> {

}
