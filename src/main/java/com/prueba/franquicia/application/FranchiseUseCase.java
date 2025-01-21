package com.prueba.franquicia.application;

import com.prueba.franquicia.domain.constants.MessageConstants;
import com.prueba.franquicia.domain.exceptions.RecordNameFoundException;
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

    public void updateFranchiseName(Long franchiseId, String franchiseName) throws FranchiseNotFoundException, RecordNameFoundException {
        this.nameNoExistsToOtherFranchise(franchiseId, franchiseName);
        Franchise franchise = getFranchiseById(franchiseId);
        franchise.setName(franchiseName);
        this.franchiseRepository.updateFranchiseName(franchise);
    }

    public void createFranchise(String franchiseName) throws RecordNameFoundException {
        Franchise franchise = new Franchise();
        franchise.setName(franchiseName);
        this.verifyFranchiseNameNotExists(franchiseName);
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

    public void verifyFranchiseNameNotExists(String franchiseName) throws RecordNameFoundException {
        Franchise franchise = this.franchiseRepository.getFranchiseByName(franchiseName);
        if(franchise != null) throw new RecordNameFoundException(MessageConstants.FRANCHISE_BY_NAME_FOUND);
    }

    public void nameNoExistsToOtherFranchise(Long franchiseId, String franchiseName) throws RecordNameFoundException {
        Franchise franchise = this.franchiseRepository.getFranchiseByNameOfDifferentId(franchiseId, franchiseName);
        if(franchise != null) throw new RecordNameFoundException("this name exists to other branch");
    }

    public static FranchiseResponse mapFranchise(Franchise franchise) {
        return new FranchiseResponse(franchise.getId(), franchise.getName());
    }
}
