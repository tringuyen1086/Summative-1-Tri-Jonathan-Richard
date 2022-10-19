package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.repository.ConsoleRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    ConsoleRepository consoleRepository;
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles(){
        return consoleRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> getConsoleById(@PathVariable int id){
        return Optional.of(consoleRepository.getReferenceById(id));
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer){
        return consoleRepository.findByManufacturer(manufacturer);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewConsole(@RequestBody Console console){
        consoleRepository.save(console);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsoleById(@RequestBody Console console ,@PathVariable int id){
        Optional<Console> foundConsole = consoleRepository.findById(id);
        if(foundConsole.isPresent()) {
            console.setId(id);
            consoleRepository.save(console);
        }else{
            //error can be change later
            throw new IllegalArgumentException("No matches for this Id.");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsoleById(@PathVariable int id){
        serviceLayer.deleteConsoleById(id);
    }
}

