package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDBRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name = ?1")
    Product getProductByName(String franchiseName);

    @Query("select p from Product p where p.id != ?1 and p.name = ?2")
    Product getProductByNameWithDifferentId(Long productId, String productName);

    @Query("select p from Product p where p.branch.id = ?1")
    List<Product> productsOfBranch(Long branchId);
}
