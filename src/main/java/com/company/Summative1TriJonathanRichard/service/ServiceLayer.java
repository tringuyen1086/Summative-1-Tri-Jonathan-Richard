package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.Invoice;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.*;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceLayer {

    private GameRepository gameRepository;

    private ConsoleRepository consoleRepository;

    private TShirtRepository tshirtRepository;

    private ProcessingFeeRepository processingFeeRepository;

    private SalesTaxRateRepository salesTaxRateRepository;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository,
                        TShirtRepository tshirtRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRateRepository salesTaxRateRepository, InvoiceRepository invoiceRepository) {
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.invoiceRepository = invoiceRepository;
    }

    // Game
    @Transactional
    public Game saveGame(Game game) {
        game = gameRepository.save(game);
        return game;
    }

    public List<Game> findAllGames() {

        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    public Game findGameById(int id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game.get();
        } else {
            throw new IllegalArgumentException("There is no match for this Game Id");
        }
    }

    public List<Game> findGameByStudio(String studio){
        return null;
    }

    public List<Game> findGameByEsrbRating(String esrbRating){
        return null;
    }

    public List<Game> findGameByTitle(String title){
        return null;
    }

    @Transactional
    public Game updateGame(Game game){
        Optional<Game> updateGame = gameRepository.findById(game.getId());
        if(updateGame.isPresent()) {
           return gameRepository.save(game);

        } else {
            throw new IllegalArgumentException("There is no match for this game.");
        }
    }


    @Transactional
    public void deleteGame(int id){
        gameRepository.deleteById(id);
    }

// Console
    public void deleteConsoleById(int id){
        Optional<Console> desiredDelete = consoleRepository.findById(id);
        if(desiredDelete.isPresent()){
            consoleRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Cannot find any matches for this ID");
        }
    }

// TShirt
    public void deleteTShirtById(int id) {
        Optional<TShirt> deleteThis = tshirtRepository.findById(id);
        if(deleteThis.isPresent()) {
            tshirtRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("No matches for this Id");
        }
    }

    public TShirt updateTShirtById( TShirt model, int id){
        Optional<TShirt> updateThis = tshirtRepository.findById(id);
        if(updateThis.isPresent()) {
            model.setId(id);
            tshirtRepository.save(model);
        }else{
            throw new IllegalArgumentException("No matches for this Id.");
        }
        return null;
    }

// Invoice

}
