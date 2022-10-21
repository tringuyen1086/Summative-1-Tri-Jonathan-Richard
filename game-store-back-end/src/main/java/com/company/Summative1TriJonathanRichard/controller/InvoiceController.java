package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.Invoice;
import com.company.Summative1TriJonathanRichard.repository.InvoiceRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice addInvoice(@RequestBody @Valid  Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Integer id){
        return serviceLayer.findInvoiceById(id);
    }

    @GetMapping("/customer/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByName(@PathVariable String name){
        return serviceLayer.findInvoiceByName(name);
    }
}
