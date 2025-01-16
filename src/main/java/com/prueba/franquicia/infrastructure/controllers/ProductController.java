package com.prueba.franquicia.infrastructure.controllers;

import com.prueba.franquicia.domain.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.BranchNotFoundException;
import com.prueba.franquicia.domain.exceptions.ProductNotFoundException;
import com.prueba.franquicia.domain.exceptions.StockException;
import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.domain.response.ProductResponse;
import com.prueba.franquicia.application.BranchUseCase;
import com.prueba.franquicia.application.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductUseCase productUseCase;

    @Autowired
    private BranchUseCase branchUseCase;

    @PostMapping(path = "/add")
    public ResponseEntity<String> saveProduct(@RequestBody ProductResponse productResponse) {

        try {
            Branch findBranch = branchUseCase.getBranchById(productResponse.getBranchId());
            if(findBranch == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
            this.productUseCase.saveProduct(productResponse.getName(), productResponse.getStock(), findBranch);
        } catch (BranchNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("product saved");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam Long branchId,
                                                @RequestParam Long productId) {
        try {
            Branch findBranch = branchUseCase.getBranchById(branchId);
            if(findBranch == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
            this.productUseCase.deleteProduct(productId);
        } catch (BranchNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("product deleted");
    }

    @PutMapping("/{productId}/{stock}")
    public ResponseEntity<String> updateProductStock(@PathVariable Long productId,
                                                     @PathVariable Integer stock) {
        try {
            this.productUseCase.updateProductStock(productId, stock);
        } catch (ProductNotFoundException | StockException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("product stock updated");
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<String> updateProductName(@PathVariable Long productId, @RequestParam String name) {
        try {
            productUseCase.updateProductName(productId, name);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("product name updated");
    }
}
