package com.prueba.franquicia.infrastructure;

import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.domain.repositories.BranchRepository;
import com.prueba.franquicia.infrastructure.database.BranchDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    @Autowired
    private BranchDBRepository branchRepository;

    @Override
    public Branch createBranch(Branch branch) {
        return this.branchRepository.save(branch);
    }

    @Override
    public void updateBranchName(Branch branch) {
        this.branchRepository.save(branch);
    }

    @Override
    public Branch getBranchById(Long branchId) {
        return this.branchRepository.findById(branchId).orElse(null);
    }

    @Override
    public List<Branch> getAllBranches() {
        return (List<Branch>) this.branchRepository.findAll();
    }

    @Override
    public Branch getBranchByName(String branchName) {
        return this.branchRepository.getBranchByName(branchName);
    }

    @Override
    public Branch getBranchByNameOfDifferentId(Long branchId, String branchName) {
        return this.branchRepository.getBranchByNameWithDifferentId(branchId, branchName);
    }
}
