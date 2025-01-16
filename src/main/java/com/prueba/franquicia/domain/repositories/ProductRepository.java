package com.prueba.franquicia.domain.repositories;

import com.prueba.franquicia.domain.models.Product;

public interface ProductRepository {
    void saveProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    void updateProduct(Product product);
}
