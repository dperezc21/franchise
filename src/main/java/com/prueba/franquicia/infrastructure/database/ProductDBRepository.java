package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDBRepository extends CrudRepository<Product, Long> {
}
