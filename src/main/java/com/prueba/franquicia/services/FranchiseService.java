package com.prueba.franquicia.services;

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
}
