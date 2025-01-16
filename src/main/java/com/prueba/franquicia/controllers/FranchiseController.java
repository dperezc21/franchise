package com.prueba.franquicia.controllers;

import com.prueba.franquicia.application.FranchiseService;
import com.prueba.franquicia.domain.exceptions.FranchiseNotFoundException;
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
            this.franchiseService.createFranchise(name);
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("franchise name updated");
    }
}
