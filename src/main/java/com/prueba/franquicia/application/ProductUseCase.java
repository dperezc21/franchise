package com.prueba.franquicia.application;

import com.prueba.franquicia.domain.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.ProductNotFoundException;
import com.prueba.franquicia.domain.exceptions.StockException;
import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.domain.models.Product;
import com.prueba.franquicia.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(String productName, Integer productStock, Branch branchFound) {
        Product product = new Product(productName, productStock);
        product.setBranch(branchFound);
        productRepository.saveProduct(product);
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Product productToDelete = productRepository.getProductById(productId);
        if(productToDelete == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        productRepository.deleteProduct(productToDelete.getId());
    }

    public void updateProductStock(Long productId, Integer stock) throws ProductNotFoundException, StockException {
        if(stock < 0) throw new StockException("stock can't be less that cero");

        Product findProduct = getProductById(productId);
        if(findProduct == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        findProduct.setStock(stock);
        productRepository.updateProduct(findProduct);
    }

    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId);
    }

    public void updateProductName(Long productId, String name) throws ProductNotFoundException {
        Product product = getProductById(productId);
        if(product == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        product.setName(name);
        productRepository.updateProduct(product);
    }
}
