package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.repository.*;
import org.junit.Before;

import static org.junit.Assert.*;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;
    ConsoleRepository consoleRepository;
    GameRepository gameRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    TShirtRepository tShirtRepository;

    @Before
    public void setUp() throws Exception {

        serviceLayer = new ServiceLayer(gameRepository,consoleRepository,tShirtRepository,processingFeeRepository,salesTaxRateRepository,invoiceRepository);
    }
}