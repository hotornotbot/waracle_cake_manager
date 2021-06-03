package com.waracle.cakemanager.web;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CakeRestController {

    private CakeService cakeService;

    public CakeRestController(@Autowired CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping(path = "/cakes/downloadall", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cake> listAllCakes(){
        return cakeService.getAllCakes();
    }
}
