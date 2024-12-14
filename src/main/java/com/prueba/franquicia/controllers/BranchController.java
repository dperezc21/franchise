package com.prueba.franquicia.controllers;

import com.prueba.franquicia.exceptions.BranchNotFoundException;
import com.prueba.franquicia.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.response.BranchResponse;
import com.prueba.franquicia.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/{branchId}")
    public ResponseEntity<String> updateBranchName(@PathVariable Long branchId, @RequestParam String name) {
        try {
            branchService.updateBranchName(branchId, name);
        } catch (BranchNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("branch name updated");
    }
}
