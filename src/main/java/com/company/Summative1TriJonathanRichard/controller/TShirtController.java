package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.TShirtRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tshirt")

public class TShirtController {

    @Autowired
    ServiceLayer service;
    @Autowired
    TShirtRepository rpo;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirts(){
        return rpo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TShirt> getTShirtById(@PathVariable int id){
        return Optional.of(rpo.getReferenceById(id));
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color){
        return rpo.findByColor(color);
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size){
        return rpo.findBySize(size);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTShirt(@RequestBody TShirt tShirt){
        rpo.save(tShirt);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirtById(@RequestBody TShirt tShirt ,@PathVariable int id){
        // This will go into service layer later...
        Optional<TShirt> updateThis = rpo.findById(id);
        if(updateThis.isPresent()) {
            //System.out.println(updateThis);
            tShirt.setId(id);
            rpo.save(tShirt);
        }else{
            //error can be change later
            throw new IllegalArgumentException("No matches for this Id.");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirtById(@PathVariable int id){
        // This will go into service layer later..
        Optional<TShirt> deleteThis = rpo.findById(id);
        if(deleteThis.isPresent()) {
            rpo.deleteById(id);
        }else {
            throw new IllegalArgumentException("No matches for this Id");
        }
    }
}
