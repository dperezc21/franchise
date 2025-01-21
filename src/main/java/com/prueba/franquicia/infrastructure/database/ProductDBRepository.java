package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductDBRepository extends CrudRepository<Product, Long> {
    @Query("select f from Franchise f where f.name = ?1")
    Product getProductByName(String name);
}
