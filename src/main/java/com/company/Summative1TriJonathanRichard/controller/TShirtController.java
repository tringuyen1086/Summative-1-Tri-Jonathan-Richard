package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.TshirtRepository;
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
    ServiceLayer serviceLayer;

    @Autowired
    TshirtRepository tshirtRepository;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirts(){
        return tshirtRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TShirt> getTShirtById(@PathVariable int id){
        return Optional.of(tshirtRepository.getReferenceById(id));
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color){
        return tshirtRepository.findByColor(color);
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size){
        return tshirtRepository.findBySize(size);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTShirt(@RequestBody TShirt tShirt){
        tshirtRepository.save(tShirt);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirtById(@RequestBody TShirt tShirt ,@PathVariable int id){
        // This will go into service layer later...
        Optional<TShirt> updateThis = tshirtRepository.findById(id);
        if(updateThis.isPresent()) {
            //System.out.println(updateThis);
            tShirt.setId(id);
            tshirtRepository.save(tShirt);
        }else{
            //error can be change later
            throw new IllegalArgumentException("No matches for this Id.");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirtById(@PathVariable int id){
        // This will go into service layer later..
        Optional<TShirt> deleteThis = tshirtRepository.findById(id);
        if(deleteThis.isPresent()) {
            tshirtRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("No matches for this Id");
        }
    }
}
