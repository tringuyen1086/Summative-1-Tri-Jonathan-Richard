package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;
    private Invoice invoice1;
    private Invoice invoice2;

    @Before
    public void setUp() throws Exception{
        invoiceRepository.deleteAll();
        invoice1 = new Invoice("customer1","100 main st","clovis","CA","93612","tshirt",1,1);
        invoice1.setTotal(12.58);
        invoice1.setTax(0.60);
        invoice1.setSubtotal(10);
        invoice1.setUnitPrice(10);
        invoice1.setProcessingFee(1.98);
        invoice2 = new Invoice("customer2","street2","city2","DE","78900","tshirt",2,1);
        invoice2.setTotal(12.48);
        invoice2.setTax(0.50);
        invoice2.setSubtotal(10);
        invoice2.setUnitPrice(10);
        invoice2.setProcessingFee(1.98);
    }

    @Test
    public void shouldReturnAllInvoicesInCollection(){
        invoiceRepository.save(invoice1);
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertEquals(invoiceList.size(),1);
        invoiceRepository.save(invoice2);
        invoiceList = invoiceRepository.findAll();
        assertEquals(invoiceList.size(),2);
    }

    @Test
    public void shouldCreateAnInvoiceInCollection() {
        invoice1 = invoiceRepository.save(invoice1);
        Optional<Invoice> presentInvoice = invoiceRepository.findById(invoice1.getId());
        assertEquals(presentInvoice.get(),invoice1);
    }

    @Test
    public void shouldReturnInvoiceById(){
        invoice1 = invoiceRepository.save(invoice1);
        invoice2 = invoiceRepository.save(invoice2);
        Optional<Invoice> customer1 = invoiceRepository.findById(invoice1.getId());
        Optional<Invoice> customer2 = invoiceRepository.findById(invoice2.getId());
        assertEquals(customer2.get(),invoice2);
        assertEquals(customer1.get(),invoice1);
    }

    @Test
    public void shouldReturnInvoiceByCustomerName(){
        invoice1 = invoiceRepository.save(invoice1);
        invoice2 = invoiceRepository.save(invoice2);
        List<Invoice> customer1 = invoiceRepository.findByName(invoice1.getName());
        List<Invoice> customer2 = invoiceRepository.findByName(invoice2.getName());
        assertEquals(customer2.size(),1);
        assertEquals(customer1.size(),1);
        assertEquals(customer1.get(0),invoice1);
        assertEquals(customer2.get(0),invoice2);
    }
}