package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Invoice;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;

    private List<Invoice> invoiceList = new ArrayList<>();

    private Invoice inputInvoice;
    private Invoice outputInvoice;
    private String inputJson;
    private String outputJson;

    @Before
    public void setUp() throws Exception {
        Invoice invoice1;
        Invoice invoice2;
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
        invoice2.setId(1);
        invoice2.setId(2);
        invoiceList.add(invoice1);
        invoiceList.add(invoice2);

        inputInvoice = new Invoice("customer1","100 main st","clovis","CA","93612","tshirt",1,1);
        inputInvoice.setTotal(12.58);
        inputInvoice.setTax(0.60);
        inputInvoice.setSubtotal(10);
        inputInvoice.setUnitPrice(10);
        inputInvoice.setProcessingFee(1.98);
        inputJson = mapper.writeValueAsString(inputInvoice);
        outputInvoice = new Invoice("customer1","100 main st","clovis","CA","93612","tshirt",2,1);
        outputInvoice.setTotal(12.58);
        outputInvoice.setTax(0.60);
        outputInvoice.setSubtotal(10);
        outputInvoice.setUnitPrice(10);
        outputInvoice.setProcessingFee(1.98);
        outputInvoice.setId(1);
        outputJson = mapper.writeValueAsString(outputInvoice);
    }

    @Test
    public void shouldReturnAllInvoicesInCollection() throws Exception{
        String listJson = mapper.writeValueAsString(invoiceList);
        doReturn(invoiceList).when(serviceLayer).findAllInvoices();
        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(listJson));
    }

    @Test
    public void shouldReturnInvoiceById() throws Exception{
        doReturn(outputInvoice).when(serviceLayer).findInvoiceById(1);
        mockMvc.perform(get("/invoice/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnInvoiceByCustomerName() throws Exception{
        List<Invoice> customerList = new ArrayList<>(Arrays.asList(inputInvoice));
        String customerListJson = mapper.writeValueAsString(customerList);
        doReturn(customerList).when(serviceLayer).findInvoiceByName("customer1");
        mockMvc.perform(get("/invoice/customer/customer1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(customerListJson));
    }

    @Test
    public void shouldCreateInvoice() throws Exception {
        doReturn(outputInvoice).when(serviceLayer).saveInvoice(inputInvoice);
        mockMvc.perform(post("/invoice")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }
}
