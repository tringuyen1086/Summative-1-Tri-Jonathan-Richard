package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.controller.ConsoleController;
import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;
//    @MockBean
//    ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddAndGetConsole(){
        Console newConsole = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        newConsole = consoleRepository.save(newConsole);
        List<Console> consoleList = consoleRepository.findAll();
        assertEquals(consoleList.size(), 1);
        consoleRepository.deleteById(newConsole.getId());
        consoleList = consoleRepository.findAll();
        assertEquals(consoleList.size(),0);
    }

    @Test
    public void shouldGetAllConsoles(){
        Console newConsole = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        newConsole = consoleRepository.save(newConsole);
        Console newConsole2 = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        newConsole = consoleRepository.save(newConsole2);
        List<Console> consoleList = consoleRepository.findAll();
        assertEquals(consoleList.size(),2);
    }

    @Test
    public void shouldUpdateConsole(){
        Console newConsole = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        newConsole = consoleRepository.save(newConsole);
        newConsole.setModel("playstation5");
        assertEquals(newConsole.getModel(),"playstation5");

    }

    @Test
    public void shouldDeleteConsole(){
        Console newConsole = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        newConsole = consoleRepository.save(newConsole);
        List<Console> consoleList = consoleRepository.findAll();
        consoleRepository.deleteById(newConsole.getId());
        consoleList = consoleRepository.findAll();
        assertEquals(consoleList.size(),0);
    }

    @Test
    public void shouldGetConsoleByManufacturer(){
        Console newConsole = consoleRepository.save(new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        Console newConsole2 = consoleRepository.save(new Console("wii", "nintendo", "32gb", "maxwell", 239.99, 2 ));
        Console newConsole3 = consoleRepository.save(new Console("playstation", "sony", "32gb", "maxwell", 239.99, 2 ));
        consoleRepository.save(newConsole);
        consoleRepository.save(newConsole2);
        consoleRepository.save(newConsole3);
        List<Console> sonyManufacturer = consoleRepository.findByManufacturer("sony");
        List<Console> nintendoManufacturer = consoleRepository.findByManufacturer("nintendo");
        assertEquals(sonyManufacturer.size(), 1);
        assertEquals(nintendoManufacturer.size(), 2);
    }
}