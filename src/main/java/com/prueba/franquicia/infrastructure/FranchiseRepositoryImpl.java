package com.prueba.franquicia.infrastructure;

import com.prueba.franquicia.domain.models.Franchise;
import com.prueba.franquicia.domain.repositories.FranchiseRepository;
import com.prueba.franquicia.infrastructure.database.FranchiseDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositoryImpl implements FranchiseRepository {

    @Autowired
    private FranchiseDBRepository franchiseDBRepository;

    @Override
    public void createFranchise(Franchise franchise) {
        franchiseDBRepository.save(franchise);
    }

    @Override
    public void updateFranchiseName(Franchise franchise) {
        franchiseDBRepository.save(franchise);
    }

    @Override
    public Franchise getFranchiseById(Long franchiseId) {
        return franchiseDBRepository.findById(franchiseId).orElse(null);
    }

    @Override
    public List<Franchise> getFranchises() {
        return (List<Franchise>) franchiseDBRepository.findAll();
    }

    @Override
    public Franchise getRecordByName(String recordName) {
        return this.franchiseDBRepository.getFranchiseByName(recordName);
    }
}
