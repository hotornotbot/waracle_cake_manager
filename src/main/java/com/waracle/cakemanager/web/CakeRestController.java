package com.waracle.cakemanager.web;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.service.CakeService;
import com.waracle.cakemanager.storage.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CakeRestController {

    private CakeService cakeService;

    public CakeRestController(@Autowired CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping(path = "/cakes", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Cake> listAllCakes(){
        return cakeService.getAllCakes();
    }

    @PostMapping(path = "/cakes")
    public void addCake(@RequestParam("title") String cakeTitle,
                        @RequestParam("description") String description,
                        @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Cake cake = new Cake(cakeTitle, description, fileName);

        //reassign cake after persisting to access the id field
        cake = cakeService.addCake(cake);

        String uploadDir = "cake-photos/" + cake.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
}
