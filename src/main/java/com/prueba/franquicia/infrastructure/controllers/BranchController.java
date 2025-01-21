package com.prueba.franquicia.infrastructure.controllers;

import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.domain.exceptions.BranchNotFoundException;
import com.prueba.franquicia.domain.exceptions.RecordNameFoundException;
import com.prueba.franquicia.domain.response.BranchResponse;
import com.prueba.franquicia.application.BranchUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/branches")
public class BranchController {

    @Autowired
    private BranchUseCase branchUseCase;

    @PostMapping(path = "/add")
    public ResponseEntity<String> saveBranchOfFranchise(@RequestBody BranchResponse branchResponse) {
        try {
            this.branchUseCase.saveBranch(branchResponse);
        } catch (FranchiseNotFoundException | RecordNameFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("branch created");
    }

    @PutMapping(path = "/{branchId}")
    public ResponseEntity<String> updateBranchName(@PathVariable Long branchId, @RequestParam String name) {
        try {
            branchUseCase.updateBranchName(branchId, name);
        } catch (BranchNotFoundException | RecordNameFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("branch name updated");
    }
}
