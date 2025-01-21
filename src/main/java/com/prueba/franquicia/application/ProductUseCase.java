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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    public void saveProduct(String productName, Integer productStock, Long branchId) throws BranchNotFoundException, RecordNameFoundException {
        Branch branchFound = this.branchRepository.getBranchById(branchId);
        if(branchFound == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
        this.verifyProductNameNotExists(productName, branchId);
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

    public void updateProductName(Long productId, String name, Long branchId) throws ProductNotFoundException, RecordNameFoundException, BranchNotFoundException {
        Branch findBranch = this.branchRepository.getBranchById(branchId);
        if(findBranch == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
        this.nameNoExistsToOtherProduct(productId, name, branchId);
        Product product = getProductById(productId);
        if(product == null) throw new ProductNotFoundException(MessageConstants.PRODUCT_NOT_FOUND);
        product.setName(name);
        productRepository.updateProduct(product);
    }

    public void verifyProductNameNotExists(String productName, Long branchId) throws RecordNameFoundException {
        Product product = this.productRepository.getProductByName(productName, branchId);
        if(product != null) throw new RecordNameFoundException(MessageConstants.PRODUCT_NAME_EXISTS);
    }

    public void nameNoExistsToOtherProduct(Long productId, String productName, Long branchId) throws RecordNameFoundException {
        Product product = this.productRepository.getProductByNameOfDifferentId(productId, productName, branchId);
        if(product != null) throw new RecordNameFoundException(MessageConstants.PRODUCT_NAME_EXISTS);
    }

    // TODO: change later for more specific queries
    public List<Product> getLargestStockProductByBranch() {
        List<Product> productList = new ArrayList<>();

        this.branchRepository.getAllBranches().forEach(branch -> {
            this.productRepository.getProductsOfBranch(branch.getId())
                    .stream().max(Comparator.comparingLong(Product::getStock)).ifPresent(productList::add);
        });

        return productList;
    }
}
