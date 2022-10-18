package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceLayer {

    GameRepository gameRepository;

    ConsoleRepository consoleRepository;

    TshirtRepository tshirtRepository;

    ProcessingFeeRepository processingFeeRepository;

    SalesTaxRateRepository salesTaxRateRepository;

    InvoiceRepository invoiceRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository,
                        TshirtRepository tshirtRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRateRepository salesTaxRateRepository, InvoiceRepository invoiceRepository) {
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Game saveGame (Game model){
        return null;
    }

    public List<Game> findAllGames(){
        return null;
    }

    public Game findGameById (Integer id){
        return null;

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
    public void updateGame(Game model){
        Game game = new Game();
        game.setId(model.getId());
        game.setTitle(model.getTitle());
        game.setEsrbRating(model.getEsrbRating());
        game.setDescription(model.getDescription());
        game.setPrice(model.getPrice());
        game.setStudio(model.getStudio());
        game.setQuantity(model.getQuantity());

        gameRepository.save(game);
    }

    @Transactional
    public void deleteGame(int id){
        gameRepository.deleteById(id);
    }






}
