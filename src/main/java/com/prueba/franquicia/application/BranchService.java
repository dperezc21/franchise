package com.prueba.franquicia.application;

import com.prueba.franquicia.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.domain.repositories.BranchRepository;
import com.prueba.franquicia.domain.repositories.FranchiseRepository;
import com.prueba.franquicia.domain.exceptions.BranchNotFoundException;
import com.prueba.franquicia.domain.models.Branch;
import com.prueba.franquicia.domain.models.Franchise;
import com.prueba.franquicia.response.BranchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    public void saveBranch(BranchResponse branchResponse) throws FranchiseNotFoundException {

        Franchise findFranchise = franchiseRepository.getFranchiseById(branchResponse.getFranchiseId());
        if(findFranchise == null) throw new FranchiseNotFoundException(MessageConstants.FRANCHISE_NOT_FOUND);
        Branch branchToSave = new Branch();
        branchToSave.setName(branchResponse.getBranchName());
        branchToSave.setFranchise(findFranchise);
        Branch branchSaved = branchRepository.createBranch(branchToSave);
        branchResponse.setId(branchSaved.getId());
    }

    public Branch getBranchById(Long branchId) {
        return branchRepository.getBranchById(branchId);
    }

    public void updateBranchName(Long branchId, String branchName) throws BranchNotFoundException {
        Branch branch = getBranchById(branchId);
        if(branch == null) throw new BranchNotFoundException(MessageConstants.BRANCH_NOT_FOUND);
        branch.setName(branchName);
        branchRepository.updateBranchName(branch);
    }
}
