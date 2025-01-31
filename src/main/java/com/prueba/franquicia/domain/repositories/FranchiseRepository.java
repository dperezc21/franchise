package com.prueba.franquicia.domain.repositories;

import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.domain.models.Franchise;

import java.util.List;

public interface FranchiseRepository {
    void createFranchise(Franchise franchise);
    void updateFranchiseName(Franchise franchise) throws FranchiseNotFoundException;
    Franchise getFranchiseById(Long franchiseId) throws FranchiseNotFoundException;
    List<Franchise> getFranchises();
    Franchise getFranchiseByName(String name);
    Franchise getFranchiseByNameOfDifferentId(Long franchiseId, String name);
}
