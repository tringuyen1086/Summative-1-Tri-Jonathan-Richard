package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.TShirtRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer service;
    private List<TShirt> tshirtList = new ArrayList<>();
    private TShirt inputTShirt;
    private TShirt outputTShirt;
    private String inputJson;
    private String outputJson;

    @Before
    public void setUp() throws Exception{
        TShirt tshirt1;
        TShirt tshirt2;
        tshirt1 = new TShirt("large","blue","desciption",10.80,100);
        tshirt2 = new TShirt("medium","red","desciption",10.80,100);
        tshirt1.setId(1);
        tshirt2.setId(2);
        tshirtList.add(tshirt1);
        tshirtList.add(tshirt2);

        inputTShirt = new TShirt("medium","red","desciption",10.80,100);
        inputJson = mapper.writeValueAsString(inputTShirt);
        outputTShirt = new TShirt("medium","red","desciption",10.80,100);
        outputTShirt.setId(1);
        outputJson = mapper.writeValueAsString(outputTShirt);
    }

    @Test
    public void shouldReturnAllTShirtsInCollection() throws Exception{
        String listJson = mapper.writeValueAsString(tshirtList);
        doReturn(tshirtList).when(service).tShirtGetAll();
        mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(listJson));
    }

    @Test
    public void shouldReturnTShirtById() throws Exception{
        doReturn(Optional.of(outputTShirt)).when(service).tShirtById(1);
        mockMvc.perform(get("/tshirt/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTShirtByColor() throws Exception{
        List<TShirt> blueShirts = new ArrayList<>(Arrays.asList(tshirtList.get(0)));
        outputJson = mapper.writeValueAsString(blueShirts);
        doReturn(blueShirts).when(service).tShirtByColor("blue");
        mockMvc.perform(get("/tshirt/color/blue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTShirtBySize() throws Exception{
        List<TShirt> mediumShirts = new ArrayList<>(Arrays.asList(tshirtList.get(1)));
        outputJson = mapper.writeValueAsString(mediumShirts);
        doReturn(mediumShirts).when(service).tShirtBySize("medium");
        mockMvc.perform(get("/tshirt/size/medium"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldCreateANewTShirt() throws Exception {
        doReturn(outputTShirt).when(service).tShirtCreate(inputTShirt);
        mockMvc.perform(post("/tshirt")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateTShirt() throws Exception {
        service.tShirtCreate(inputTShirt);
        mockMvc.perform(put("/tshirt/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTShirt() throws Exception{
        service.tShirtCreate(inputTShirt);
        mockMvc.perform(delete("/tshirt/1"))
                .andExpect(status().isNoContent());
    }
}