package com.prueba.franquicia.controllers;

import com.prueba.franquicia.models.Franchise;
import com.prueba.franquicia.repository.FranchiseRepository;
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
    private FranchiseRepository franchiseRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewFranchise(@RequestParam String name) {
        Franchise franchise = new Franchise();
        franchise.setName(name);
        franchiseRepository.save(franchise);
        return "Saved";
    }
}
