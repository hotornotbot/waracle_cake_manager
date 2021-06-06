package com.waracle.cakemanager.web;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import com.waracle.cakemanager.storage.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class CakeController {

    private static final String ADD_CAKE = "addCake";

    private CakeService cakeService;

    public CakeController(@Autowired CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping("/")
    public String displayAllCakes(Model model) {
        model.addAttribute("cakedisplay", cakeService.getAllCakes());
        return "cakedisplay";
    }

    @GetMapping("/addCake")
    public String addCakeForm(Model model) {
        model.addAttribute(ADD_CAKE, new Cake());
        return ADD_CAKE;
    }

    @PostMapping("/addCake")
    public String cakeSubmit(@ModelAttribute Cake cake, Model model,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {

        //handle photo
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        cake.setImageUrl(fileName);

        //reassign cake after persisting to access the id field
        cake = cakeService.addCake(cake);

        String uploadDir = "cake-photos/" + cake.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        model.addAttribute(ADD_CAKE, cake);
        return "result";
    }

}
