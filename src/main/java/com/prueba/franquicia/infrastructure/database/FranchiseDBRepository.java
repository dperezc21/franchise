package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Franchise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FranchiseDBRepository extends CrudRepository<Franchise, Long> {
    @Query("select f from Franchise f where f.name = ?1")
    Franchise getFranchiseByName(String name);

    @Query("select f from Franchise f where f.id != ?1 and f.name = ?2")
    Franchise getFranchiseByNameWithDifferentId(Long productId, String productName);
}
