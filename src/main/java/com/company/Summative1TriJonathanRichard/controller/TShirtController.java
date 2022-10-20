package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.TShirtRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tshirt")
public class TShirtController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirts(){
        return serviceLayer.tShirtGetAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TShirt> getTShirtById(@PathVariable int id){
        return serviceLayer.tShirtById(id);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color){
        return serviceLayer.tShirtByColor(color);
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size){
        return serviceLayer.tShirtBySize(size);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt createNewTShirt(@RequestBody @Valid TShirt tShirt){
        return serviceLayer.tShirtCreate(tShirt);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TShirt updateTShirtById(@RequestBody @Valid TShirt tShirt ,@PathVariable int id){
        return serviceLayer.updateTShirtById(tShirt,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirtById(@PathVariable int id){
        serviceLayer.deleteTShirtById(id);
    }
}
