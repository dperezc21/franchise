package com.prueba.franquicia.domain.repositories;

import com.prueba.franquicia.domain.models.Product;

import java.util.List;

public interface ProductRepository extends RecordNameRepository<Product> {
    void saveProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    void updateProduct(Product product);
    List<Product> getProductsOfBranch(Long branchId);
}
