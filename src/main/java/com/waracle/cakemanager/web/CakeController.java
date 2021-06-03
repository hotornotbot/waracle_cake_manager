package com.waracle.cakemanager.web;

import com.waracle.cakemanager.service.CakeService;
import com.waracle.cakemanager.entity.Cake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CakeController {

    private CakeService cakeService;

    public CakeController(@Autowired CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping("/")
    public String displayAllCakes(Model model){
        model.addAttribute("cakedisplay", cakeService.getAllCakes());
        return "cakedisplay";
    }

    @GetMapping("/addCake")
    public String addCakeForm(Model model){
        model.addAttribute("addCake", new Cake());
        return "addCake";
    }

    @PostMapping("/addCake")
    public String cakeSubmit(@ModelAttribute Cake cake, Model model) {
        cakeService.addCake(cake);
        model.addAttribute("addCake", cake);
        return "result";
    }

}
