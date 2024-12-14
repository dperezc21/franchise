package com.prueba.franquicia.repository;

import com.prueba.franquicia.models.Branch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Long> {

    /*@Query(nativeQuery = true,
    value = "select * from branch where fk_franchise=:franchiseId")
    List<Branch>getBranchByFranchiseId(@Param("franchiseId") Long franchiseId);*/
}
