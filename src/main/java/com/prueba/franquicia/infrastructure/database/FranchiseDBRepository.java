package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Franchise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FranchiseDBRepository extends CrudRepository<Franchise, Long> {
    @Query("select l from Franchise l where l.name = ?1")
    Franchise getFranchiseByName(String name);
}
