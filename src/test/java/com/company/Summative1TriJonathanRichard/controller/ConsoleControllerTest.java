package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.ConsoleRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;
    @MockBean
    private ConsoleRepository consoleRepository;
    private List<Console> consoleList = new ArrayList<>();
    private Console inputConsole;
    private Console outputConsole;
    private String inputJson;
    private String outputJson;

    @Before
    public void setUp() throws Exception{
        Console console1;
        Console console2;
        console1 = new Console("nintendo switch", "nintendo", "32gb", "maxwell", 239.99, 2);
        console2 = new Console("playstation 5", "sony", "825gb", "zen", 500.00, 1);
        console1.setId(1);
        console2.setId(2);
        consoleList.add(console1);
        consoleList.add(console2);

        inputConsole = new Console("playstation 5", "sony", "825gb", "zen", 500.00, 1);
        inputJson = mapper.writeValueAsString(inputConsole);
        outputConsole = new Console("playstation 5", "sony", "825gb", "zen", 500.00, 1);
        outputConsole.setId(1);
        outputJson = mapper.writeValueAsString(outputConsole);
    }

    @Test
    public void shouldReturnAllConsolesInCollection() throws Exception{
        String listJson = mapper.writeValueAsString(consoleList);
        doReturn(consoleList).when(serviceLayer).findAllConsoles();
        mockMvc.perform(get("/console"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(listJson));
    }

    @Test
    public void shouldReturnConsoleById() throws Exception{
        doReturn(Optional.of(outputConsole)).when(serviceLayer).findConsoleById(1);
        mockMvc.perform(get("/console/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(outputJson));
    }

    @Test
    public void shouldReturnConsoleByManufacturer() throws Exception{
        List<Console> sonyManufacturer = new ArrayList<>(Arrays.asList(consoleList.get(1)));
        outputJson = mapper.writeValueAsString(sonyManufacturer);
        doReturn(sonyManufacturer).when(serviceLayer).findConsoleByManufacturer("sony");
        mockMvc.perform(get("/console/manufacturer/sony"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(outputJson));
    }



    @Test
    public void shouldCreateANewConsole() throws Exception {
        doReturn(outputConsole).when(serviceLayer).saveConsole(inputConsole);
        mockMvc.perform(post("/console")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(outputJson));
    }

    @Test
    public void shouldUpdateConsole() throws Exception {
        serviceLayer.saveConsole(inputConsole);
        mockMvc.perform(put("/console/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteConsole() throws Exception{
        serviceLayer.saveConsole(inputConsole);
        mockMvc.perform(delete("/console/1"))
                .andExpect(status().isNoContent());
    }
}