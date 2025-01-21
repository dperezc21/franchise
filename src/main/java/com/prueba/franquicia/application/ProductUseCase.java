package com.prueba.franquicia.application;

import com.prueba.franquicia.domain.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.BranchNotFoundException;
import com.prueba.franquicia.domain.exceptions.ProductNotFoundException;
import com.prueba.franquicia.domain.exceptions.RecordNameFoundException;
import com.prueba.franquicia.domain.exceptions.StockException;
import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.domain.models.Product;
import com.prueba.franquicia.domain.repositories.BranchRepository;
import com.prueba.franquicia.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    public void saveProduct(String productName, Integer productStock, Long branchId) throws BranchNotFoundException, RecordNameFoundException {
        this.verifyProductNameNotExists(productName);
        Branch branchFound = this.branchRepository.getBranchById(branchId);
        if(branchFound == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
        Product product = new Product(productName, productStock);
        product.setBranch(branchFound);
        productRepository.saveProduct(product);
    }

    public void deleteProduct(Long productId, Long branchId) throws ProductNotFoundException, BranchNotFoundException {
        Branch findBranch = this.branchRepository.getBranchById(branchId);
        if(findBranch == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
        Product productToDelete = this.getProductById(productId);
        productRepository.deleteProduct(productToDelete.getId());
    }

    public void updateProductStock(Long productId, Integer stock) throws ProductNotFoundException, StockException {
        if(stock < 0) throw new StockException("stock can't be less that cero");
        Product findProduct = getProductById(productId);
        findProduct.setStock(stock);
        productRepository.updateProduct(findProduct);
    }

    public Product getProductById(Long productId) throws ProductNotFoundException {
        Product product = productRepository.getProductById(productId);
        if(product == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        return product;
    }

    public void updateProductName(Long productId, String name) throws ProductNotFoundException {
        Product product = getProductById(productId);
        if(product == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        product.setName(name);
        productRepository.updateProduct(product);
    }

    public void verifyProductNameNotExists(String productName) throws RecordNameFoundException {
        Product product = this.productRepository.getRecordByName(productName);
        if(product != null) throw new RecordNameFoundException(MessageConstants.BRANCH_BY_NAME_FOUND);
    }
}
