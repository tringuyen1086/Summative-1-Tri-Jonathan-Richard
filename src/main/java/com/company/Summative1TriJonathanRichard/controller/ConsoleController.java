package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.exceptions.NotFoundException;
import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;


    private static int idCounter = 1;

    private static List<Console> consoleList = new ArrayList<>(Arrays.asList(
            new Console(idCounter++,"Nintendo Switch", "Nintendo", "350MB", "I7", 5.99, 4 ),
            new Console(idCounter++,"XBox One", "Microsoft", "1TB", "I5", 9.99, 2 ),
            new Console(idCounter++,"Playstation", "Sony", "250MB", "I7", 5.99, 5),
            new Console(idCounter++,"Sega Dreamcast", "Sega", "1mB", "Peanut", 1.00, 1 )
    ));

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel consoleViewModel) {
        //console.setConsole_id(idCounter++);
        //consoleList.add(console);
        return serviceLayer.saveConsole(consoleViewModel);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles(){
        return consoleList;
    }

    @GetMapping(value="get/{consoleId}") // need to revise the path
    @ResponseStatus(value = HttpStatus.OK)
    public Console getConsoleById(@PathVariable int consoleId){
        Console foundConsole = null;

        for(Console console: consoleList){
            if (console.getConsole_id() == consoleId){
                foundConsole = console;
                break;
            }

        }
        if (foundConsole == null) {
            throw new NotFoundException("Console not found in database");
        }
        return foundConsole;
    }
    @RequestMapping(value = "/put/{consoleId}", method = RequestMethod.PUT) // need to revise the path
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateConsoleById(@PathVariable int consoleId, @RequestBody @Valid Console console) {

        if( console.getConsole_id() == 0 ) {
            console.setConsole_id(consoleId);
        }

        if( console.getConsole_id() != consoleId) {
            throw new IllegalArgumentException("Id in parameter must match the ID in the request body");
        }

        int index = -1;

        for(int i = 0; i < consoleList.size(); i++) {
            if(consoleList.get(i).getConsole_id() == consoleId) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            consoleList.set(index, console);
        }
    }
    @RequestMapping(value = "/delete/{consoleId}", method = RequestMethod.DELETE) // need to revise the path
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteConsoleById(@PathVariable int consoleId) {
        int index = -1;

        for(int i = 0; i < consoleList.size(); i++) {
            if(consoleList.get(i).getConsole_id() == consoleId) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            consoleList.remove(index);
        }
        else throw new NotFoundException("Console not found.");
    }
    ServiceLayer service;
}

