package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.repository.GameRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)

public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();
    private List<Game> gameList = new ArrayList<>();
    private Game inputGame;
    private Game outputGame;
    private String inputJson;
    private String outputJson;


    @Before
    public void setUp() throws Exception{
        Game game1;
        Game game2;
        game1 = new Game("A Plague Tale: Requiem", "M (Mature 17+)", "description",  59.99, "studio", 10);
        game2 = new Game("Saint Kotar", "T (Teen)", "description", 34.99, "studio", 10);

        game1.setId(1);
        game1.setId(2);
        gameList.add(game1);
        gameList.add(game2);

        inputGame = new Game("Saint Kotar", "T (Teen)", "description",  34.99, "studio", 10);
        inputJson = mapper.writeValueAsString(inputGame);
        outputGame = new Game("Saint Kotar", "T (Teen)", "description",  34.99, "studio", 10);
        outputGame.setId(1);
        outputJson = mapper.writeValueAsString(outputGame);

    }

    @Test
    public void shouldReturnAllGamesInCollection() throws Exception{
        String listJson = mapper.writeValueAsString(gameList);
        doReturn(gameList).when(serviceLayer).findAllGames();
        mockMvc.perform(get("/game"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(listJson));
    }

    @Test
    public void shouldReturnGameById() throws Exception{
        doReturn(outputGame).when(serviceLayer).findGameById(1);
        mockMvc.perform(get("/game/{id}", 1)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }




}