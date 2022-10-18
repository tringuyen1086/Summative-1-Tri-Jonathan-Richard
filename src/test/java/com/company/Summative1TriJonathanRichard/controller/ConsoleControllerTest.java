package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldReturnAllConsoles() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setModel("Nintendo Switch");
        inputConsole.setManufacturer("Nintendo");
        inputConsole.setMemory_amount("350MB");
        inputConsole.setProcessor("I7");
        inputConsole.setDecimal(5.99);
        inputConsole.setQuantity(4);

        String inputConsoleJson = mapper.writeValueAsString(inputConsole);

        Console outputConsole = new Console();
        outputConsole.setConsole_id(1);
        outputConsole.setModel("Nintendo Switch");
        outputConsole.setManufacturer("Nintendo");
        outputConsole.setMemory_amount("350MB");
        outputConsole.setProcessor("I7");
        outputConsole.setDecimal(5.99);
        outputConsole.setQuantity(4);

        String outputConsoleJson = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(get("console"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());;



    }


}