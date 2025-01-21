package com.prueba.franquicia.infrastructure.database;

import com.prueba.franquicia.domain.models.Branch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BranchDBRepository extends CrudRepository<Branch, Long> {
    @Query("select b from Branch b where b.name = ?1")
    Branch getBranchByName(String name);

    @Query("select b from Branch b where b.id != ?1 and b.name = ?2")
    Branch getBranchByNameWithDifferentId(Long branchId, String branchName);
}
