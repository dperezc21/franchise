package com.prueba.franquicia.controllers;

import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.FranchiseRepository;
import com.prueba.franquicia.response.BranchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<BranchResponse> saveBranchOfFranchise(@RequestBody BranchResponse branchResponse) {

        Franchise findFranchise = franchiseRepository.findById(branchResponse.getFranchiseId()).orElse(null);
        if(findFranchise == null) return ResponseEntity.badRequest().body(null);
        Branch branchToSave = new Branch();
        branchToSave.setName(branchResponse.getBranchName());
        branchToSave.setFranchise(findFranchise);
        Branch branchSaved = branchRepository.save(branchToSave);
        branchResponse.setId(branchSaved.getId());
        return ResponseEntity.ok(branchResponse);
    }
}
