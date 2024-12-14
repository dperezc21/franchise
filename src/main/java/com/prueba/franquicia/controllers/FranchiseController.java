package com.prueba.franquicia.controllers;

import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.FranchiseRepository;
import com.prueba.franquicia.services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/franchise")
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
}
