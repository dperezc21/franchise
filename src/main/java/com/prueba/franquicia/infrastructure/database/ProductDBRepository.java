package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDBRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name = ?1 and p.branch.id = ?2")
    Product getProductByName(String franchiseName, Long branchId);

    @Query("select p from Product p where p.id != ?1 and p.name = ?2 and p.branch.id = ?3")
    Product getProductByNameWithDifferentId(Long productId, String productName, Long branchId);

    @Query("select p from Product p where p.branch.id = ?1")
    List<Product> productsOfBranch(Long branchId);
}
