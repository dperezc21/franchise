package com.prueba.franquicia.controllers;

import com.prueba.franquicia.exceptions.FranchiseNotFoundException;
import com.prueba.franquicia.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewFranchise(@RequestParam String name) {
        try{
            this.franchiseService.saveFranchise(name);
        } catch (Exception e) {
            System.out.println("error while save franchise");
        }
        return "Saved";
    }

    @PutMapping(path = "/{franchiseId}")
    public ResponseEntity<String> updateFranchiseName(@PathVariable Long franchiseId, @RequestParam String name) {
        try {
            franchiseService.updateFranchiseName(franchiseId, name);
        } catch (FranchiseNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("franchise name updated");
    }
}
