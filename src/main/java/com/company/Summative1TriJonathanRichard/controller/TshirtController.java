package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tshirt")

public class TshirtController {

    @Autowired
    ServiceLayer serviceLayer;

}
