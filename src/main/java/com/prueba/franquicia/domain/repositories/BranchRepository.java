package com.prueba.franquicia.domain.repositories;

import com.prueba.franquicia.domain.models.Branch;

import java.util.List;

public interface BranchRepository {
    Branch createBranch(Branch branch);
    void updateBranchName(Branch branch);
    Branch getBranchById(Long branchId);
    List<Branch> getAllBranches();
    Branch getBranchByName(String name);
    Branch getBranchByNameOfDifferentId(Long branchId, String name);
}
