package com.prueba.franquicia.services;

import com.prueba.franquicia.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.FranchiseRepository;
import com.prueba.franquicia.response.BranchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    public BranchResponse saveBranch(BranchResponse branchResponse) throws FranchiseNotFoundException {

        Franchise findFranchise = franchiseRepository.findById(branchResponse.getFranchiseId()).orElse(null);
        if(findFranchise == null) throw new FranchiseNotFoundException("franchise not exists");
        Branch branchToSave = new Branch();
        branchToSave.setName(branchResponse.getBranchName());
        branchToSave.setFranchise(findFranchise);
        Branch branchSaved = branchRepository.save(branchToSave);
        branchResponse.setId(branchSaved.getId());
        return branchResponse;
    }

    public Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId).orElse(null);
    }
}
