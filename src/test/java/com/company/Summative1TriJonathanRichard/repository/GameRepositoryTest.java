package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.Game;
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
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Before
    public void setUp() throws Exception{

        gameRepository.deleteAll();
    }

    @Test
    public void shouldCreateGameUsingGameRepository(){
        Game game = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);
        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(),game);
    }

    @Test
    public void getAllGamesUsingGameRepository() {
        Game game = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);
        List<Game> gameList = gameRepository.findAll();
        assertEquals(gameList.size(),1);
        game = new Game("Title2", "T", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);
        gameList = gameRepository.findAll();
        assertEquals(gameList.size(),2);
    }

    @Test
    public void getGameByIdUsingGameRepository() {
        Game game = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);
        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);
        gameRepository.findById(game.getId());
        game1 = gameRepository.findById(game.getId());
        assertTrue(game1.isPresent());
    }
    @Test
    public void shouldDeleteGameUsingGameRepository() {
        Game game = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);
        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(),game);
        gameRepository.deleteById(game.getId());
        game1 = gameRepository.findById(game.getId());
        assertFalse(game1.isPresent());
    }

    @Test
    public void updateGame(){
        Game game = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        game = gameRepository.save(game);

        game.setTitle("Title2");
        game.setEsrbRating("T");
        game.setDescription("description");
        game.setPrice(34.99);
        game.setStudio("studio1");
        game.setQuantity(10);
        gameRepository.save(game);

        Optional<Game> game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(),game);
    }

    @Test
    public void getGameByStudio(){
        Game game1 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game2 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game3 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        Game game4 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        List<Game> studio1Studio = gameRepository.findByStudio("studio1");
        List<Game> studio2Studio = gameRepository.findByStudio("studio2");
        assertEquals(studio1Studio.size(),2);
        assertEquals(studio2Studio.size(),2);
    }

    @Test
    public void getGameByEsrbRating(){
        Game game1 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game2 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game3 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        Game game4 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        List<Game> mEsrbRating = gameRepository.findByEsrbRating("M");
        List<Game> tEsrbRating = gameRepository.findByEsrbRating("T");
        assertEquals(mEsrbRating.size(),2);
        assertEquals(tEsrbRating.size(),2);
    }

    @Test
    public void getGameByTitle(){
        Game game1 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game2 = new Game("Title1", "M", "description",  34.99, "studio1", 10);
        Game game3 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        Game game4 = new Game("Title2", "T", "description",  34.99, "studio2", 10);
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);
        List<Game> title1Title = gameRepository.findByTitle("Title1");
        List<Game> title2Title = gameRepository.findByTitle("Title2");
        assertEquals(title1Title.size(),2);
        assertEquals(title2Title.size(),2);
    }

}