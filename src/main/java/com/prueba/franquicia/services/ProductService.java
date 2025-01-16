package com.prueba.franquicia.services;

import com.prueba.franquicia.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.ProductNotFoundException;
import com.prueba.franquicia.domain.exceptions.StockException;
import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.models.Product;
import com.prueba.franquicia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(String productName, Integer productStock, Branch branchFound) {
        Product product = new Product(productName, productStock);
        product.setBranch(branchFound);
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Product productToDelete = productRepository.findById(productId).orElse(null);
        if(productToDelete == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        productRepository.deleteById(productToDelete.getId());
    }

    public void updateProductStock(Long productId, Integer stock) throws ProductNotFoundException, StockException {
        if(stock < 0) throw new StockException("stock can't be less that cero");

        Product findProduct = getProductById(productId);
        if(findProduct == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        findProduct.setStock(stock);
        productRepository.save(findProduct);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public void updateProductName(Long productId, String name) throws ProductNotFoundException {
        Product product = getProductById(productId);
        if(product == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        product.setName(name);
        productRepository.save(product);
    }
}
