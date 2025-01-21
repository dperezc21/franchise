package com.prueba.franquicia.infrastructure;

import com.prueba.franquicia.domain.models.Product;
import com.prueba.franquicia.domain.repositories.ProductRepository;
import com.prueba.franquicia.infrastructure.database.ProductDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Product> getProductsOfBranch(Long branchId) {
        return this.productDBRepository.productsOfBranch(branchId);
    }

    @Override
    public Product getProductByName(String productName, Long branchId) {
        return this.productDBRepository.getProductByName(productName, branchId);
    }

    @Override
    public Product getProductByNameOfDifferentId(Long id, String name, Long branchId) {
        return this.productDBRepository.getProductByNameWithDifferentId(id, name, branchId);
    }
}
