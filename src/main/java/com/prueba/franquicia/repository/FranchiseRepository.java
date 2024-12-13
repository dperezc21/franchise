package com.prueba.franquicia.repository;

import com.prueba.franquicia.models.Franchise;
import org.springframework.data.repository.CrudRepository;

public interface FranchiseRepository extends CrudRepository<Franchise, Integer> {
}
