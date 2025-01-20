package com.prueba.franquicia.application;

import com.prueba.franquicia.domain.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.domain.models.Franchise;
import com.prueba.franquicia.domain.repositories.FranchiseRepository;
import com.prueba.franquicia.domain.response.FranchiseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseUseCase {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public void updateFranchiseName(Long franchiseId, String franchiseName) throws FranchiseNotFoundException {
        Franchise franchise = getFranchiseById(franchiseId);
        franchise.setName(franchiseName);
        this.franchiseRepository.updateFranchiseName(franchise);
    }


    public void createFranchise(String franchiseName) {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseName);
        this.franchiseRepository.createFranchise(franchise);
    }

    public Franchise getFranchiseById(Long franchiseId) throws FranchiseNotFoundException {
        Franchise findFranchise = this.franchiseRepository.getFranchiseById(franchiseId);
        if(findFranchise == null) throw new FranchiseNotFoundException(MessageConstants.FRANCHISE_NOT_FOUND);
        return findFranchise;
    }

    public List<Franchise> getAllFranchise() {
        return this.franchiseRepository.getFranchises();
    }

    public static FranchiseResponse mapFranchise(Franchise franchise) {
        return new FranchiseResponse(franchise.getId(), franchise.getName());
    }
}
