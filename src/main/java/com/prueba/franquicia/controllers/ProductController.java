package com.prueba.franquicia.controllers;

import com.prueba.franquicia.exceptions.BranchNotFoundException;
import com.prueba.franquicia.exceptions.ProductNotFoundException;
import com.prueba.franquicia.exceptions.StockException;
import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.response.ProductResponse;
import com.prueba.franquicia.services.BranchService;
import com.prueba.franquicia.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BranchService branchService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> saveProduct(@RequestBody ProductResponse productResponse) {

        try {
            Branch findBranch = branchService.getBranchById(productResponse.getBranchId());
            if(findBranch == null) throw new BranchNotFoundException("branch not found");
            this.productService.saveProduct(productResponse.getName(), productResponse.getStock(), findBranch);
        } catch (BranchNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("product saved");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam Long branchId,
                                                @RequestParam Long productId) {
        try {
            Branch findBranch = branchService.getBranchById(branchId);
            if(findBranch == null) throw new BranchNotFoundException("branch not found");
            this.productService.deleteProduct(productId);
        } catch (BranchNotFoundException | ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("product deleted");
    }

    @PutMapping("/{productId}/{stock}")
    public ResponseEntity<String> updateProductStock(@PathVariable Long productId,
                                                     @PathVariable Integer stock) {
        try {
            this.productService.updateProductStock(productId, stock);
        } catch (ProductNotFoundException | StockException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("product stock updated");
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<String> updateProductName(@PathVariable Long productId, @RequestParam String name) {
        try {
            productService.updateProductName(productId, name);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("product name updated");
    }
}
