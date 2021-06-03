package com.waracle.cakemanager.service;

import com.waracle.cakemanager.entity.Cake;
import com.waracle.cakemanager.storage.CakeRepository;
import com.waracle.cakemanager.web.CakeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class CakeServiceTests {

    @InjectMocks
    CakeService cakeService;

    @Mock
    CakeRepository cakeRepository;

    @Test
    public void testGetAllCakes(){

        Cake cake1  = new Cake("lemon drizzle", "lemony cake", "lemondrizzle.jpg");
        Cake cake2  = new Cake("orange drizzle", "orange cake", "orangedrizzle.jpg");
        Cake cake3  = new Cake("apple drizzle", "apple cake", "applerizzle.jpg");

        List<Cake> cakeList = Arrays.asList(cake1, cake2, cake3);

        Mockito.when(cakeRepository.findAll()).thenReturn(cakeList);

        List<Cake> result = cakeService.getAllCakes();

        assertEquals( 3, result.size());

    }
}
