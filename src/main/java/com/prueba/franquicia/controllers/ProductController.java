package com.prueba.franquicia.controllers;

import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.models.Product;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.ProductRepository;
import com.prueba.franquicia.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<String> saveProduct(@RequestBody ProductResponse productResponse) {

        Branch findBranch = branchRepository.findById(productResponse.getBranchId()).orElse(null);
        if(findBranch == null) return ResponseEntity.badRequest().body("Branch not found");

        Product product = new Product(productResponse.getName(), productResponse.getStock());
        product.setBranch(findBranch);
        productRepository.save(product);
        return ResponseEntity.ok().body("product saved");
    }

    @DeleteMapping("/{branchId}/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("branchId") Long branchId,
                                                @PathVariable("productId") Long productId) {
        Branch findBranch = branchRepository.findById(branchId).orElse(null);
        if(findBranch == null) return ResponseEntity.badRequest().body("branch not exists");
        Product productToDelete = productRepository.findById(productId).orElse(null);
        if(productToDelete == null) return ResponseEntity.badRequest().body("product not exists");
        productRepository.deleteById(productToDelete.getId());
        return ResponseEntity.ok().body("product deleted");
    }

    @PutMapping("/{productId}/{stock}")
    public ResponseEntity<String> updateProductStock(@PathVariable Long productId,
                                                     @PathVariable Integer stock) {
        if(stock < 0) return ResponseEntity.badRequest().body("stock can't be less that cero");

        Product findProduct = productRepository.findById(productId).orElse(null);
        if(findProduct == null) return ResponseEntity.badRequest().body("product not exists");
        findProduct.setStock(stock);
        productRepository.save(findProduct);
        return ResponseEntity.ok("product stock updated");
    }
}
