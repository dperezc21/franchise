package com.prueba.franquicia.infrastructure.controllers;

import com.prueba.franquicia.application.FranchiseUseCase;
import com.prueba.franquicia.domain.exceptions.FranchiseNameFoundException;
import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.domain.response.FranchiseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseUseCase franchiseUseCase;

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> addNewFranchise(@RequestParam String name) {
        try{
            this.franchiseUseCase.createFranchise(name);
        } catch (FranchiseNameFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Saved");
    }

    @PutMapping(path = "/{franchiseId}")
    public ResponseEntity<String> updateFranchiseName(@PathVariable Long franchiseId, @RequestParam String name) {
        try {
            franchiseUseCase.updateFranchiseName(franchiseId, name);
        } catch (FranchiseNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("franchise name updated");
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<FranchiseResponse>> getAllProducts() {
        List<FranchiseResponse> productResponses;
        try {
            productResponses = franchiseUseCase.getAllFranchise().stream().map(FranchiseUseCase::mapFranchise).toList();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok(productResponses);
    }
}
