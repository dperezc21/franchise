package com.prueba.franquicia.domain.repositories;

import com.prueba.franquicia.domain.models.Branch;

public interface BranchRepository extends RecordNameRepository<Branch> {
    Branch createBranch(Branch branch);
    void updateBranchName(Branch branch);
    Branch getBranchById(Long branchId);

}
