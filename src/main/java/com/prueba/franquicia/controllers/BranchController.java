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

import java.util.Optional;

@Controller
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<BranchResponse> saveBranchOfFranchise(@RequestBody BranchResponse branch) {
        Branch branch1 = new Branch();

        Franchise findFranchise = franchiseRepository.findById(branch.getFranchiseId()).orElse(null);
        if(findFranchise == null) return ResponseEntity.badRequest().body(null);

        branch1.setName(branch.getBranchName());
        branch1.setFranchise(findFranchise);
        Branch branchSaved = branchRepository.save(branch1);
        branch1.setId(branchSaved.getId());
        return ResponseEntity.ok(branch);
    }
}
