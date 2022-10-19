package com.company.Summative1TriJonathanRichard.controller;

import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.repository.GameRepository;
import com.company.Summative1TriJonathanRichard.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    ServiceLayer serviceLayer;
    @PostMapping()

    @ResponseStatus(HttpStatus.CREATED)

    public Game createNewGame(@RequestBody @Valid  Game game) {

        return serviceLayer.saveGame(game);

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return serviceLayer.findAllGames();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Integer id){
        return serviceLayer.findGameById(id);

    }

    @GetMapping("/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return (List<Game>) serviceLayer.findGameByStudio(studio);
    }

    @GetMapping("/esrbRating/{esrbRating}")
    public List<Game> getGameByEsrbRating(@PathVariable String esrbRating) {
        return serviceLayer.findGameByEsrbRating(esrbRating);
    }

    @GetMapping("/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {
        return serviceLayer.findGameByTitle(title);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        serviceLayer.updateGame(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        serviceLayer.deleteGame(id);
    }

}
