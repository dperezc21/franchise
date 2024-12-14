package com.prueba.franquicia.services;

import com.prueba.franquicia.models.Product;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.FranchiseRepository;
import com.prueba.franquicia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(Long franchise) {
        //this.branchRepository.getBranchByFranchiseId(franchise).forEach(System.out::println);
        return null;
    }
}
