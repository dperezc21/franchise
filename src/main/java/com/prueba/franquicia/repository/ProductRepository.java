package com.prueba.franquicia.repository;

import com.prueba.franquicia.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
