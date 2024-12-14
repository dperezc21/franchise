package com.prueba.franquicia.controllers;

import com.prueba.franquicia.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.models.Branch;
import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.BranchRepository;
import com.prueba.franquicia.repository.FranchiseRepository;
import com.prueba.franquicia.response.BranchResponse;
import com.prueba.franquicia.services.BranchService;
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
    private BranchService branchService;

    @PostMapping(path = "/add")
    public ResponseEntity<BranchResponse> saveBranchOfFranchise(@RequestBody BranchResponse branchResponse) {
        try {
            this.branchService.saveBranch(branchResponse);
        } catch (FranchiseNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(branchResponse);
    }
}
