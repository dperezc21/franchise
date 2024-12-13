package com.prueba.franquicia.controllers;

import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.models.Product;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.ProductRepository;
import com.prueba.franquicia.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println(findBranch);
        product.setBranch(findBranch);
        productRepository.save(product);
        return ResponseEntity.ok().body(productResponse.toString());
    }
}
