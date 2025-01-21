package com.prueba.franquicia.infrastructure;

import com.prueba.franquicia.domain.models.Product;
import com.prueba.franquicia.domain.repositories.ProductRepository;
import com.prueba.franquicia.infrastructure.database.ProductDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductDBRepository productDBRepository;

    @Override
    public void saveProduct(Product product) {
        this.productDBRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productDBRepository.deleteById(productId);
    }

    @Override
    public Product getProductById(Long productId) {
        return this.productDBRepository.findById(productId).orElse(null);
    }

    @Override
    public void updateProduct(Product product) {
        this.saveProduct(product);
    }

    @Override
    public Product getRecordByName(String productName) {
        return this.productDBRepository.getProductByName(productName);
    }

    @Override
    public Product getRecordByNameOfDifferentId(Long id, String name) {
        return this.productDBRepository.getProductByNameWithDifferentId(id, name);
    }
}
