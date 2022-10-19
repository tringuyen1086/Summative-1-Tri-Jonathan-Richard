package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.repository.ConsoleRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    ConsoleRepository consoleRepository;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console createNewConsole(@RequestBody @Valid Console console){
        return serviceLayer.saveConsole(console);
    }
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles(){
        return serviceLayer.findAllConsoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable Integer id){
        return serviceLayer.findConsoleById(id);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<Console>> getConsoleByManufacturer(@PathVariable String manufacturer){
        return consoleRepository.findByManufacturer(manufacturer);
    }


    @PutMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        serviceLayer.updateConsole(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsoleById(@PathVariable int id){
        serviceLayer.deleteConsole(id);
    }
}

