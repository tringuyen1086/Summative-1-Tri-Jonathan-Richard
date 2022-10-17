package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.service.GameStoreServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    GameStoreServiceLayer service;
}
