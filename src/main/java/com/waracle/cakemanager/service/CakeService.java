package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.storage.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CakeService {

    private CakeRepository cakeRepository;

    public CakeService(@Autowired  CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public List<Cake> getAllCakes() {

                return StreamSupport.stream(cakeRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }

    public Cake addCake(Cake cake){
        return cakeRepository.save(cake);
    }
}
