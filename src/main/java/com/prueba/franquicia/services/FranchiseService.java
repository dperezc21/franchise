package com.prueba.franquicia.services;

import com.prueba.franquicia.constants.MessageConstants;
import com.prueba.franquicia.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public void saveFranchise(String franchiseName) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseName);
        franchiseRepository.save(franchise);
    }

    public void updateFranchiseName(Long franchiseId, String franchiseName) throws FranchiseNotFoundException {
        Franchise franchise = getFranchiseById(franchiseId);
        franchise.setName(franchiseName);
        franchiseRepository.save(franchise);
    }

    public Franchise getFranchiseById(Long franchiseId) throws FranchiseNotFoundException {
        Franchise findFranchise = franchiseRepository.findById(franchiseId).orElse(null);
        if(findFranchise == null) throw new FranchiseNotFoundException(MessageConstants.FRANCHISE_NOT_FOUND);
        return findFranchise;
    }
}
